;(function($){
	var dataSingle_function = function()
	{
		var self = this;
		this.body = $(document.body);
		this.autoParentHeight();
		this.detail = true;
		$.scrollTo(".nl_main_container", 300);
		this.body.delegate(".nl_main_info_detail_btn", "click", function(){
			if(self.detail){
				self.detail = false;
				$(".nl_main_info_detail").css("display", "block");
				$(".nl_main_info_detail_btn").html(">> 收起");
				setTimeout(function(){
					self.autoParentHeight();
				}, 100);
			}else{
				self.detail = true;
				$(".nl_main_info_detail").css("display", "none");
				$(".nl_main_info_detail_btn").html(">> 全部");
				setTimeout(function(){
					self.autoParentHeight();
				}, 100);
			}
			
		});
		this.body.delegate(".nl_main_info_remind_collection", "click", function(){
			self.collection($(this));
		});
		window.onresize = function(){
			self.autoParentHeight();
		}
		// this.test();
	};
	dataSingle_function.prototype = {
		test:function()
		{
			var self = this;
			yy_init($(document).width());
		},
		autoParentHeight:function()
		{
			var self = this;
			var screenWidth = parseInt($(document).width());
			var leftHeight = parseInt($(".nl_recommend_left").css("height"));
			var rightHeight = parseInt($(".nl_recommend_right").css("height"));
			if(screenWidth > 750){
				if(rightHeight > leftHeight){
					$(".nl_recommend_container").css("height", rightHeight + "px");
				}else{
					$(".nl_recommend_container").css("height", leftHeight + "px");
				}
			}else{
				$(".nl_recommend_container").css("height", leftHeight + 30 + rightHeight + "px");
			}
		},
		collection:function(obj)
		{
			var self = this;
			var url = obj.attr("data-url");
			var imgurl = obj.attr("data-imgurl");
			var describe = obj.attr("data-describe");
			var name = obj.attr("data-name");
			$.ajax({
				url:"/data/collection.html",
				type:"GET",
				dataType:"json",
				data:{"url":url, "imgurl":imgurl, "describe":describe, "name":name},
				success:function(data){
					if(data.text == "ok"){
						yy_init("收藏成功");
					}else if(data.text == "exist"){
						yy_init("已经收藏此菜谱");
					}else if(data.text == "log"){
						yy_init("请先登录");
					}else{
						yy_init("系统错误");
					}
				},
				error:function(data, status, e){
					console.log(e);
				}
			});
		}
	},
	window['dataSingle_function'] = dataSingle_function;
})(jQuery);