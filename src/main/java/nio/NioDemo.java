package nio;

import org.junit.Test;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class NioDemo {

    @Test
    public void testBuffer() {
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        buffer.put("hello world".getBytes());

        System.out.println(buffer.capacity());

        System.out.println(buffer.position());
        buffer.flip();//从写模式转为读模式
        byte[] dest = new byte[buffer.limit()];
        buffer.get(dest);
        System.out.println(new String(dest));
    }

    //获取通道有三种方式

    /**
     * 1.通过流获取
     *
     * @throws Exception
     */
    @Test
    public void testChannel() throws Exception {
        try (FileInputStream fis = new FileInputStream("1.jpg");
             FileOutputStream fos = new FileOutputStream("2.jpg");
             FileChannel inChannel = fis.getChannel();
             FileChannel outChannel = fos.getChannel();
        ) {
            writeToChannel(inChannel, outChannel);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void writeToChannel(FileChannel inChannel, FileChannel outChannel) throws IOException {
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        while (inChannel.read(buffer) != -1) {
            buffer.flip();
            outChannel.write(buffer);
            buffer.clear();
        }
    }

    /**
     * 直接内存的方式写,速度快.
     */
    private void wirte2ChannelByMaped(FileChannel inChannel, FileChannel outChannel) throws IOException {
        MappedByteBuffer inBuf = inChannel.map(FileChannel.MapMode.READ_ONLY, 0, inChannel.size());
        MappedByteBuffer outBuf = outChannel.map(FileChannel.MapMode.READ_WRITE, 0, inChannel.size());

        byte[] dest = new byte[1024];
        inBuf.get(dest);
        outBuf.put(dest);
    }

    /**
     * 2.通过静态方法 open 来获取
     */
    @Test
    public void testOpenChannel() {
        try (
                FileChannel inChannel = FileChannel.open(Paths.get("1.jpg"), StandardOpenOption.READ);
                FileChannel outChannel = FileChannel.open(Paths.get("3.jpg"), StandardOpenOption.WRITE, StandardOpenOption.READ, StandardOpenOption.CREATE);

        ) {
            wirte2ChannelByMaped(inChannel, outChannel);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 通道之间的数据传输
     * transformFrom
     * transformTo
     */
    @Test
    public void testChannelTransform() {
        try (
                FileChannel inChannel = FileChannel.open(Paths.get("1.jpg"), StandardOpenOption.READ);
                FileChannel outChannel = FileChannel.open(Paths.get("3.jpg"), StandardOpenOption.WRITE, StandardOpenOption.READ, StandardOpenOption.CREATE);

        ) {
            inChannel.transferTo(0, inChannel.size(), outChannel);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Test
    public void testScatterGather() throws Exception {
        RandomAccessFile raf = new RandomAccessFile("a.html", "rw");
        FileChannel fileChannel = raf.getChannel();

        //分散读取
        ByteBuffer buffer1 = ByteBuffer.allocate(2);
        ByteBuffer buffer2 = ByteBuffer.allocate(1024);
        ByteBuffer[] buffers = {buffer1, buffer2};

        fileChannel.read(buffers);
        for (ByteBuffer buffer : buffers) {
            buffer.flip();
        }

        System.out.println(new String(buffers[0].array()));
        System.out.println("----------------------------------");
        System.out.println(new String(buffers[1].array()));

        //聚集写入
        RandomAccessFile raf2 = new RandomAccessFile("1.txt", "rw");
        raf2.getChannel().write(buffers);
    }

}
