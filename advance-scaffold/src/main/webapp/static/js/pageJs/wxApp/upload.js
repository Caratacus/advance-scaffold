var upload = function(selector,context) {
	return new upload.prototype.init();
}
upload.prototype = {
	init : function() {
	},
	sc : function(v,u,type,preUrl) {
		$.ajaxFileUpload({
			url : u+'../file/upload/'+type,
			secureuri:false,
			data:{"preUrl":preUrl},						// async:true,
			fileElementId:"file",
			dataType : 'json', //接受数据格式 (这里有很多,常用的有html,xml,js,json)
			timeout : 20000000,// 设置请求超时时间（毫秒）。
			contentType: "application/x-www-form-urlencoded; charset=utf-8",
			success : function(data,status) { //成功
				var data=eval(data);
				console.log(data);
				if(data.success!="1")
				{
					alert(data.error);
					console.log(data);
				}
				else{
					if(type=="book"){
						$("#url").val(data.path);
						$("#img_tr").show();
						$("#imgUrl_img").attr("src",data.imgPath);
						$("#imgUrl_a").attr("href",data.imgPath);

						//将上传的文件url保存到数据库中
						$.ajax({
							type: "post",
							url:  v,
							dataType: "json",
							data:{
								tableId:$("#bookid").val(),
								url:data.path,
								tableName:$("#tableName").val(),
								appId:$('#app').combobox('getValue')
							},
							success: function(data){
								alert(data.msg);
							}
						});
					}else{
						$("#url").val(data.path);
						if(type == "dataFileDown"){
							$("#fileMd5").val(data.fileMd5);
						}
						alert("success");
					}

				}
			}});
	}

}

upload.prototype.init.prototype = upload.prototype;