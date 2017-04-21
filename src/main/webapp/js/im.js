/**
 * Created by kun on 2017/4/18.
 */
/**************************************************** 发送方*/
function sendMessage(sendId,reciveId) {
    var contents=$("#content").val();
    $.ajax({
        type:"GET",
        url:"http://localhost:8080/messageInfo/im/send?sendId="+sendId+"&receiveId="+reciveId+"&content="+contents,
        success:function (dataObj) {
            if (dataObj.status_code==200){
                if (dataObj.data){
                    showSuccess();
                }
            }else {
                showFailed()
            }
        },
        error:function () {
            showFailed();

        }
    })

}
function showSuccess() {
    clearContent();
    $("#result").empty().append("<span class='alert alert-success'>发送成功</span>").fadeIn(200).fadeOut(1000);
}
function showFailed() {
    $("#result").empty().append("<span class='alert alert-danger'>发送失败</span>").fadeToggle(200).fadeToggle(1000);
}
function clearContent() {
    $("#content").val("");
}

/**************************************************** 接收方*/
function fetchRecord(sendId,reciveId){
    $.ajax({
        url:"http://localhost:8080/messageInfo/im/list?sendId="+sendId+"&receiveId="+reciveId,
        type:"GET",
        success:function (result) {
            showRecords(result,sendId);
        }
    })
}
function showRecords(jsonObj,sendId) {
    var recordsHtml="";
    $.each(jsonObj.data,function (i,item) {
        var isSend=(item.sendId==sendId);
        recordsHtml+="<tr><td class='warning'>"+moment(item.sendTime).startOf("minute").fromNow()+"</td>";
        if (isSend){
            recordsHtml+="<td class='success'>"+item.content+"</td><td></td>";
        }else{
            recordsHtml+="<td></td><td class='success'>"+item.content+"</td>";
        }
        recordsHtml+="</tr>";
    })
    $("table.table>tbody").empty().append(recordsHtml);
}