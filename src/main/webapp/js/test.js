;(function($){
	var test_function = function(obj)
	{
		var self = this;
		this.body = $(document.body);
		this.referer;
		var str = 'http://www.nl.com/data/getFoodData.html?food=牛肉&time=2';
		str = str.match(/\.html\?food=([\w\W]*?)/);
		console.log(str[0]);
		window.onload = function(){
			// self.getReferer(self.sendIP, obj['cip']);
		}
	};
	test_function.prototype = {
		getReferer:function(fun, ip)
		{
			var self = this;
			var width = $(document).width();
			var url = document.referrer;
			if(url){
				self.referer = url.match(/http:\/\/([\w\W]*?)\//);
				self.referer = self.referer[1];
			}else{
				self.referer = "无referer";
			}
			fun(self.referer, ip);
		},
		sendIP:function(referer, ip)
		{
			$.ajax({
				url:"/test/testGetIP.html",
				type:"POST",
				dataType:"json",
				data:{"referer":referer, "ip":ip},
				success:function(data){
					console.log(data.text);
				},
				error:function(data, status, e){
					console.log(e);
				}
			});
		}
	}
	window['test_function'] = test_function;
})(jQuery);