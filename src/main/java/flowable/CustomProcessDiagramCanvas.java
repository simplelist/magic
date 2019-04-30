package flowable;

import org.flowable.image.impl.DefaultProcessDiagramCanvas;

import java.awt.*;
import java.awt.geom.RoundRectangle2D;

/**
 * @Author kun
 * @DATETIME 2019-04-10 15:17
 * 修改工作流画图的设置
 */
public class CustomProcessDiagramCanvas extends DefaultProcessDiagramCanvas {
    protected static Color HIGHLIGHT_COLOR = new Color(99, 202, 126);

    public CustomProcessDiagramCanvas(int width, int height, int minX, int minY, String imageType, String activityFontName, String labelFontName, String annotationFontName, ClassLoader customClassLoader) {
        super(width, height, minX, minY, imageType);
        this.activityFontName = "宋体";
        this.labelFontName = "宋体";
        this.annotationFontName = "宋体";
        initialize(imageType);
    }

    public void drawHighLight(int x, int y, int width, int height) {
        Paint originalPaint = g.getPaint();
        Stroke originalStroke = g.getStroke();
        g.setPaint(HIGHLIGHT_COLOR);
        g.setStroke(THICK_TASK_BORDER_STROKE);
        RoundRectangle2D rect = new RoundRectangle2D.Double(x, y, width, height, 20, 20);
        g.draw(rect);
        g.setPaint(originalPaint);
        g.setStroke(originalStroke);
    }
}