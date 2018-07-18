;(function($){
	var index_function = function()
	{
		var self = this;
		this.body = $(document.body);
		// 用来保存上一次的页面高度  若是下一次宽度变化后高度并不比上一次大就不进行改变
		this.heightCheck = parseInt(window.innerHeight);
		// 用来保存上一次的页面宽度，若是下一次宽度变化后宽度并不比上一次大就不进行改变
		this.widthCheck = parseInt($(document).width());
		this.autoParentHeight();

		window.onresize = function(){
			self.autoParentHeight();
		}
		// this.test();
	};
	index_function.prototype = {
		// test函数用来判断屏幕宽度用于设计自适应
		test:function()
		{
			var self = this;
			var width = $(document).width();
			yy_init(width);
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
		}
	}
	window['index_function'] = index_function;
})(jQuery);