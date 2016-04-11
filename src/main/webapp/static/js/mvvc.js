/**
 * viewModel初始化
 * @author sjx
 * @param viewModel
 */
function initViewModel(viewModel){
	/**
	 * 设置固定数据信息
	 * @param tab_fixed 固定数据模型
	 * @param fixed_data 数据
	 */
	viewModel.setFixedData = function(tab_fixed,fixed_data){
		tab_fixed(fixed_data);
	}
	
	/**
	 * 设置可修改数据信息
	 * @param content 可修改数据模型
	 * @param data 可修改数据
	 * @param pk_field 可修改数据主键字段
	 * @param fields 可修改数据所有字段
	 */
	viewModel.setAvaData=function(content,data,pk_field,fields) {
		content.pk_field = pk_field;
		content.fields = fields;
		content.data(data);
		viewModel.extend(content.data_bak,data);
	}
	
	viewModel.setSglData=function(content,sgl_data) {
		content.data(sgl_data);
		viewModel.extend(content.data_bak,sgl_data);
	}
	
	viewModel.getType=function(o)
    {
        var _t;
        return ((_t = typeof(o)) == "object" ? o==null && "null" || Object.prototype.toString.call(o).slice(8,-1):_t).toLowerCase();
    }
	
	/**
	 * 深拷贝
	 */
	viewModel.extend=function(destination,source)
    {
        for(var p in source)
        {
            if(viewModel.getType(source[p])=="array"||viewModel.getType(source[p])=="object")
            {
                destination[p]=viewModel.getType(source[p])=="array"?[]:{};
                arguments.callee(destination[p],source[p]);
            }
            else
            {
                destination[p]=source[p];
            }
        }
    }
	
	/**
	 * 移除一行
	 * @param content 数据模型
	 * @param obj 待移除数据
	 */
	viewModel.remove = function(content,obj) {
		content.data.remove(obj);
	}
	
	/**
	 * 增加一行
	 * @param content 数据模型
	 */
	viewModel.add = function(content) {
		var newRowData = new Array();
		for(var field in content.fields)
			newRowData[content.fields[field]]="";
		content.data.push(newRowData);
	}
	
	/**
	 * 获得新增数据
	 * @param content 数据模型
	 */
	viewModel.getAddData = function(content) {
		var addData=new Array();
		for(var i=0,len=content.data._latestValue.length;i<len;i++){
			var data = content.data._latestValue[i];
			var data_bak;var flag = true;
			for(var j=0,lne=content.data_bak.length;j<lne;j++){
				data_bak = content.data_bak[j];
				if(data_bak[content.pk_field] == data[content.pk_field]){
					flag = false;
					break;
				}
			}
			if(flag){
				var newdata={};
				for(var k=0,lem=content.fields.length;k<lem;k++)
					newdata[content.fields[k]]=data[content.fields[k]];
				addData.push(newdata);
			}
		}
		return addData;
	}
	
	/**
	 * 获得修改数据
	 * @param content 数据模型
	 */
	viewModel.getEditData = function(content) {
		var editData=new Array();
		for(var i=0,len=content.data_bak.length;i<len;i++){
			var data_bak = content.data_bak[i];
			for(var j=0,lne=content.data._latestValue.length;j<lne;j++){
				var data = content.data._latestValue[j];
				if(data_bak[content.pk_field] == data[content.pk_field]){
					if(viewModel._eqbak(data,data_bak,content.fields)){
						editData.push(data);
					}
				}
			}
		}
		return editData;
	}
	
	/**
	 * 获得修改数据（返回字段名，修改之前值，修改之后值）
	 * @param content 数据模型
	 */
	viewModel.getOrigModiData = function(content) {
		var editData=new Array();
		for(var i=0,len=content.data_bak.length;i<len;i++){
			var data_bak = content.data_bak[i];
			for(var j=0,lne=content.data._latestValue.length;j<lne;j++){
				var data = content.data._latestValue[j];
				if(data_bak[content.pk_field] == data[content.pk_field]){
					var edata = new Object();
					var eds=new Array();
					for(var key in fields){
						if(data[key]!=data_bak[key]){
							var modifiedCol = new Object();
							modifiedCol.name = key;
							modifiedCol.oldVal = data_bak[key];
							modifiedCol.newVal = data[key];
							eds.push(modifiedCol);
						}
					}
					edata.modCols = eds;
					edata.pkVal = data[content.pk_field];
					edata.pkName = content.pk_field;
					editData.push(edata);
				}
			}
		}
		return editData;
	}
	
	/**
	 * 获得修改数据（返回字段名，修改之前值，修改之后值）
	 * @param content 数据模型
	 */
	viewModel.getSglOrigModiData = function(content,pk) {
		var data_bak = content.data_bak;
		var data = content.data._latestValue;
		var editData=new Array();
		var edata = new Object();
		var eds=new Array();
		for(var key in data){
			if(data[key]!=data_bak[key]){
				var modifiedCol = new Object();
				modifiedCol.name = key;
				modifiedCol.oldVal = data_bak[key];
				modifiedCol.newVal = data[key];
				eds.push(modifiedCol);
			}
		}
		edata.modCols = eds;
		edata.pkVal = data[pk];
		edata.pkName = pk;
		editData.push(edata);
		return editData;
	}
	
	viewModel.getSglEditData = function(content) {
		var data_bak = content.data_bak;
		var data = content.data._latestValue;
		for(var key in data){
			if(data[key]!=data_bak[key])return data;
		}
		return {};
	}
	
	viewModel._eqbak = function(_nowData,_bakData,fields) {
		for(var i=0,len = fields.length;i<len;i++){
			if(_nowData[fields[i]]!=_bakData[fields[i]])
				return true;
		}
		return false;
	}

	/**
	 * 获得删除数据
	 * @param content 数据模型
	 */
	viewModel.getDelData = function(content) {
		var delData=new Array();
		for(var i=0,len=content.data_bak.length;i<len;i++){
			var data_bak = content.data_bak[i];
			var data;var flag = true;
			for(var j=0,lne=content.data._latestValue.length;j<lne;j++){
				data = content.data._latestValue[j];
				if(data_bak[content.pk_field] == data[content.pk_field]){
					flag = false;
					break;
				}
			}
			if(flag)
				delData.push(data_bak);
		}
		return delData;
	}
	
	/**
	 * 获得固定数据
	 * @param content 数据模型
	 */
	viewModel.getFixedData = function(content) {
		return content._latestValue;
	}
};

/**
 * 生成固定数据模型
 * @param ko
 * @returns
 */
function fixedM(ko){
	return ko.observableArray([]);
};

/**
 * 生成可修改数据模型
 * @param ko
 * @returns 
 */
function avaM(ko){
	var avaModel = {};
	avaModel.pk_field = '';
	avaModel.data = ko.observableArray([]);
	avaModel.data_bak = [];
	avaModel.fields = [];
	return avaModel;
};

function SglM(ko){
	var sglModel = {};
	sglModel.data = ko.observableArray([]);
	sglModel.data_bak = [];
	return sglModel;
};