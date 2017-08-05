/******************************************************************************
函数名称:   <ATFunc></ATFunc>
函数功能:   <ATFuncDesc>页面初始化  查询默认的商品分类(一级分类下第一条分类)</ATFuncDesc>       
开发人员:   helong
修改记录:
******************************************************************************/
$(function(){
	var options = {};
    options.params = {"level":"1"};
	options.url = base+"/template/gdscls/queryGdsClsList.jhtml";
	options.successCallback = queryClsBackFn;
	oms.ajaxCall(options);
});

/******************************************************************************
函数名称:   <ATFunc>queryClsBackFn</ATFunc>
函数功能:   <ATFuncDesc>查询默认分类后  分类数据存放到页面上</ATFuncDesc>       
开发人员:   helong
修改记录:
******************************************************************************/
function queryClsBackFn(data){
	saveGdsCls(data.data[0].id,data.data[0].clsName);
	initAttr(data.data[0].id);
}

/******************************************************************************
函数名称:   <ATFunc>initAttr</ATFunc>
函数功能:   <ATFuncDesc>根据clsId  查询此分类下的商品属性数据</ATFuncDesc>       
开发人员:   helong
修改记录:
******************************************************************************/
function initAttr(clsId){
	//初始化商品属性
	var options = {};
    options.params = {"clsId":clsId};
	options.url = base+"/template/gdsAttb/queryAttbTable.jhtml";
	options.successCallback = queryAttbBackFn;
	oms.ajaxCall(options);
}

/******************************************************************************
函数名称:   <ATFunc>queryAttbBackFn</ATFunc>
函数功能:   <ATFuncDesc>根据属性数据  把数据加载到页面上 </ATFuncDesc>       
开发人员:   helong
修改记录:
******************************************************************************/
function queryAttbBackFn(data){
	var attbTable = data.data;
	var str = "";
	for(var i in attbTable){
		var attbTableInfo = attbTable[i].gdsAtvList;
		var attbName = attbTable[i].attbName || ""; 
		var attbId = attbTable[i].attbId
		str += "<tr>";
		str += "<td><span class='tit'>"+attbName+"</span>";
		str += "<input type='text' id="+attbId+" value='"+attbName+"' maxlength='32' oldAttName='"+attbName+"' onblur='getEditAttName(this)' class='u-ipt u-ipt-sm f-hidden'/>";
		str += "<a onClick='editAttName(this)' class='rech' href='javascript:'>重命名</a></td>";
		str += "<td>";
		for(var j in attbTableInfo){
			var attbValue = attbTableInfo[j].attbValue;
			var atvId = attbTableInfo[j].id;
			str += "<div class='m-propertyValue'>";
			str += "<input type='text' id="+atvId+" value="+attbValue+" maxlength='32' oldAttVal="+attbValue+" onblur='getEditAttVal(this)' class='u-ipt u-ipt-sm f-hidden'></input>";
			str += "<label><input type='checkbox'><span>"+attbValue+"</span></label>";
			str += "<a onClick='editAttVal(this)' href='javascript:' class='m-edit'></a></div>";
		}
		str += "<a href='javascript:' onClick='addAttVal(this)' class='u-btn u-btn-auto u-btn-bg-blue u-bd-color-blue f-pl40'>新增属性<i class='ico-add'></i></a></td>";
		str += "<td><a href='javascript:' onClick='delAtt(this)'>删除属性值</a></td>";
		str += "</tr>";
	}
	$("#gdsAttrInfo").html(str);
}

/******************************************************************************
函数名称:   <ATFunc>editAttName</ATFunc>
函数功能:   <ATFuncDesc>编辑属性名称 </ATFuncDesc>       
开发人员:   helong
修改记录:
******************************************************************************/
function editAttName(myself){
	$(myself).hide().siblings('span').hide();
	var getVal = $(myself).siblings('input').val();
	$(myself).siblings('input').show().val('').focus().val(getVal);
}

/******************************************************************************
函数名称:   <ATFunc>getEditAttName</ATFunc>
函数功能:   <ATFuncDesc>编辑属性名称  鼠标移开后调用此方法</ATFuncDesc>       
开发人员:   helong
修改记录:
******************************************************************************/
function getEditAttName(myself){
	var oldVal = $(myself).attr('oldAttName');
	var newVal = $.trim($(myself).val());
	if(newVal == oldVal){
		$(myself).siblings('span').show();
	}else{
		if(newVal.length<=0){
			oms.tooltip("属性名不能为空，请重新填写!","error");
			$(myself).focus().val(oldVal);
			return;
		}else{
			$(myself).siblings('span').text(newVal).show();
			$(myself).attr('oldAttName',newVal);
			var id = $(myself).attr("id");
			var attbName = $(myself).val();
			saveAttb(id , attbName , oldVal , myself);
		}
	}
	$(myself).hide();
	$(myself).siblings('a').show();	
}

/******************************************************************************
函数名称:   <ATFunc>saveAttb</ATFunc>
函数功能:   <ATFuncDesc>保存商品 属性名称</ATFuncDesc>       
开发人员:   helong
修改记录:
******************************************************************************/
function saveAttb(id , attbName ,oldVal,myself){
	if($("#clsId").val() == ""){
		oms.tooltip("未选择商品分类,不可进行此操作!","error");
	}
	var url = "";
	var param ={};
	param.attbName = attbName;
	param.clsId = $("#clsId").val();
	if(id==""){
		url = base + "/template/gdsAttb/saveAttb.jhtml";
		var options = {};
	    options.params = param;
		options.url = url;
		options.successCallback = successBack;
		options.bizErrCallback = function(data){
			$(myself).siblings('span').text('').show();
			$(myself).attr('oldAttName','');
			$(myself).val('');
		};
		oms.ajaxCall(options);
	}else{
		param.id = id;
		url = base + "/template/gdsAttb/modAttb.jhtml";
		var options = {};
	    options.params = param;
		options.url = url;
		options.successCallback = successBack;
		options.bizErrCallback = function(data){
			$(myself).siblings('span').text(oldVal).show();
			$(myself).attr('oldAttName',oldVal);
			$(myself).val(oldVal);
		};
		oms.ajaxCall(options);
	}
}

/******************************************************************************
函数名称:   <ATFunc>successBack</ATFunc>
函数功能:   <ATFuncDesc>本页面所有 请求的回调函数</ATFuncDesc>       
开发人员:   helong
修改记录:
******************************************************************************/
function successBack(data){
	oms.tooltip(data.errMsg,"succeed");
	refreshTr();
}

/******************************************************************************
函数名称:   <ATFunc>editAttVal</ATFunc>
函数功能:   <ATFuncDesc>编辑属性可选值</ATFuncDesc>       
开发人员:   helong
修改记录:
******************************************************************************/
function editAttVal(myself){
	$(myself).parent().addClass("m-propertyValue-hover");
	$("input[type='text']").not(".f-hidden").each(function(){
		$(this).remove()
	});	
	$(myself).hide().siblings('label').hide();
	var getVal = $(myself).siblings('input').val();
	$(myself).siblings('input').show().val('').focus().val(getVal);
}

/******************************************************************************
函数名称:   <ATFunc>getEditAttVal</ATFunc>
函数功能:   <ATFuncDesc>编辑属性可选值光标移开后 触发此方法</ATFuncDesc>       
开发人员:   helong
修改记录:
******************************************************************************/
function getEditAttVal(myself){
	$(myself).parent().removeClass("m-propertyValue-hover");
	var oldVal = $(myself).attr('oldAttVal');
	var newVal = $.trim($(myself).val());
	if(newVal == oldVal){
		$(myself).siblings('label').show();
	}else{
		if(newVal.length<=0){
			oms.tooltip("属性值不能为空，请重新填写!","error");
			$(myself).focus().val(oldVal);
			return;
		}else{
			$(myself).siblings('label').show().children('span').text(newVal);
			$(myself).attr('oldAttVal',newVal);
			var id = $(myself).attr("id");
			var attbValue = $(myself).val();
			saveAtv(id , attbValue);
		}
	}
	$(myself).hide();
	$(myself).siblings('a').show();
}

/******************************************************************************
函数名称:   <ATFunc>saveAtv</ATFunc>
函数功能:   <ATFuncDesc>编辑属性可选值光标移开后 触发此方法</ATFuncDesc>       
开发人员:   helong
修改记录:
******************************************************************************/
function saveAtv(id , attbValue){
	if($("#clsId").val() == ""){
		oms.tooltip("未选择商品分类,不可进行此操作","error");
	}
	
	var options = {};
    options.params = {"id":id,"attbValue":attbValue,"clsId":$("#clsId").val()};
	options.url = base + "/template/gdsAtv/modAtv.jhtml";
	options.successCallback = successBack;
	oms.ajaxCall(options);
}

/******************************************************************************
函数名称:   <ATFunc>delAtt</ATFunc>
函数功能:   <ATFuncDesc>删除属性可选值</ATFuncDesc>       
开发人员:   helong
修改记录:
******************************************************************************/
function delAtt(myself){
	var getCheckBox = $(myself).closest('td').prev('td').find('input:checked');
	if(getCheckBox.length<=0){
		oms.tooltip("请选择其中的属性值!","error");
		return;	
	}else{
		$.artDialog({
			title:'提示',
			content:'确定要删除吗?',
			ok:function(){
				//组装删除的 数据
				var delAtvId = "";
				getCheckBox.each(function(){
					var id = $(this).parent().prev("input").attr("id");
					var name = $(this).parent().prev("input").val();
					delAtvId+=","+id+":"+name;
					$(this).closest('div').remove();
				});
				var delAtv ={"delAtvId":delAtvId.substring(1, delAtvId.length)}
				
				var options = {};
			    options.params = delAtv;
				options.url = base + "/template/gdsAtv/delAtv.jhtml";
				options.successCallback = successBack;
				oms.ajaxCall(options);
			}
		});
	}
}

/******************************************************************************
函数名称:   <ATFunc>addAttVal</ATFunc>
函数功能:   <ATFuncDesc添加属性可选值</ATFuncDesc>       
开发人员:   helong
修改记录:
******************************************************************************/
function addAttVal(myself){
	if($(myself).parent('td').prev("td").find('input').val() == ""){
		oms.tooltip("请在前一个单元格，先命名此属性值的属性名!" , "error");
		return;
	}
	
	$("input[type='text']").not(".f-hidden").each(function(){
		if($(this).attr("id") !="clsId" && $(this).attr("id") !="clsName"){
			$(this).remove()
		}
	});
	
	$(myself).before('<input type="text" maxlength=32 onblur="addNewAttVal(this)" placeholder="添加属性" class="u-ipt u-ipt-sm f-mr10">');
	$(myself).prev('input').focus();
}

/******************************************************************************
函数名称:   <ATFunc>addNewAttVal</ATFunc>
函数功能:   <ATFuncDesc>页面添加属性可选值input框</ATFuncDesc>       
开发人员:   helong
修改记录:
******************************************************************************/
function addNewAttVal(myself){
	var getVal = $.trim($(myself).val());
	if(getVal.length<=0){
		oms.tooltip("属性名称不能为空!" , "error");
		return;
	}else{
		if($("#clsId").val() == ""){
			oms.tooltip("未选择商品分类,不可进行此操作!" , "error");
		}
		//操作方法
		var attbId = $(myself).parent("td").prev("td").find("input").attr("id");
		var attbValue = $(myself).val();
		var param = {"attbId":attbId,"attbValue":attbValue,"clsId":$("#clsId").val()};
		
		var options = {};
	    options.params = param;
		options.url = base + "/template/gdsAtv/addAtv.jhtml";
		options.successCallback = successBack;
		oms.ajaxCall(options);
		
		$(myself).remove();
	}
}

/******************************************************************************
函数名称:   <ATFunc>refreshTr</ATFunc>
函数功能:   <ATFuncDesc>刷新本页面属性数据</ATFuncDesc>       
开发人员:   helong
修改记录:
******************************************************************************/
function refreshTr(){
	initAttr($("#clsId").val());
}

/******************************************************************************
函数名称:   <ATFunc>addAttbForTable</ATFunc>
函数功能:   <ATFuncDesc>添加一行新的商品属性名称</ATFuncDesc>       
开发人员:   helong
修改记录:
******************************************************************************/
function addAttbForTable(){
	var str = $("#gdsAttrInfo").html();
	str += "<tr>";
	str += "<td><span class='tit'></span>";
	str += "<input type='text' id='' value='' maxlength='32' oldAttName='' onblur='getEditAttName(this)' class='u-ipt u-ipt-sm'/>";
	str += "<a onClick='editAttName(this)' class='rech' href='javascript:'>重命名</a></td>";
	str += "<td>";
	
	str += "<a href='javascript:' onClick='addAttVal(this)' class='u-btn u-btn-auto u-btn-bg-blue u-bd-color-blue f-pl40'>新增属性<i class='ico-add'></i></a></td>";
	str += "<td><a href='javascript:' onClick='delAtt(this)'>删除属性值</a></td>";
	str += "</tr>";
	$("#gdsAttrInfo").html(str);
}

/******************************************************************************
函数名称:   <ATFunc>selectGdsCls</ATFunc>
函数功能:   <ATFuncDesc>选择商品分类</ATFuncDesc>       
开发人员:   helong
修改记录:
******************************************************************************/
function selectGdsCls(){
	oms.s_addPop("商品分类选择",base+"/template/gdscls/turnGdsclsSelect.jhtml","",600,450);
}

/******************************************************************************
函数名称:   <ATFunc>saveGdsCls</ATFunc>
函数功能:   <ATFuncDesc>选择商品分类回调方法</ATFuncDesc>       
开发人员:   helong
修改记录:
******************************************************************************/
function saveGdsCls(selectClsId,selectClsName){
	$("#clsId").val(selectClsId);
	$("#clsName").val(selectClsName);
	initAttr(selectClsId);
}

