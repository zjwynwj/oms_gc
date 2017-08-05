$(function(){
	var options = {};
    options.params = {"id" : $("#id").val()};
	options.url = base_template+"/expressMgr/queryExpressInfo.jhtml";
	options.successCallback = function(data){
		$("#name").val(data.data.name);
		$("#linkMan").val(data.data.linkMan);
		$("#linkPhone").val(data.data.linkPhone);
		$("input:radio[name=actived][value="+data.data.actived+"]").attr("checked", "true");
	};
	oms.ajaxCall(options);
});

function saveClsFn(){
	var express = {}
	express.id = $("#id").val();
	express.linkMan = $("#linkMan").val();
	express.linkPhone = $("#linkPhone").val();
	express.actived = $("input[name=actived]").filter(':checked').val();
	
	var options = {};
    options.params = express;
	options.url = base_template+"/expressMgr/modExpressInfo.jhtml";
	options.successCallback = function(data){
		oms.tooltip(data.errMsg , "succeed");
		parent.search();
		closeWinFn();
	};
	oms.ajaxCall(options);
}

function closeWinFn() {
	oms.s_Pop_closedChild();
}