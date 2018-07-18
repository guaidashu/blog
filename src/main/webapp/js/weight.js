;(function($){
	var calorie_function = function()
	{
		var self = this;
		this.body = $(document.body);
		this.autoParentHeight();
		this.body.delegate(".counter_weight", "click", function(){
			calc_index();
		});
		$.scrollTo(".nl_main_container", 300);

		window.onresize = function(){
			self.autoParentHeight();
		}
		// this.test();
	};
	calorie_function.prototype = {
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
	window['calorie_function'] = calorie_function;
})(jQuery);

function calc_index(){
	var height = document.getElementById("height").value;
	var width = document.getElementById("weight").value;
	height = $.trim(height);
	width = $.trim(width);
	if(!width || !height){
		yy_init("请完善信息噢");
		return;
	}
	if(document.getElementsByName("sex")[0].checked == true){
		var std_width = (height-80)*0.7;
	}else if(document.getElementsByName("sex")[1].checked == true){
		var std_width = (height-70)*0.6;
	}else{
		yy_init("请选择性别哦");
		return;
	}
	std_width = std_width.toFixed(1);
	var over_width = width - std_width;
	over_width = over_width/std_width;
	document.getElementById("std_width").innerHTML = std_width+"(kg)";
	document.getElementById("nl_status").innerHTML = "";
	if(over_width>-0.1&&over_width<0.1){
		document.getElementById("nl_status").innerHTML = "正常范围(相关疾病发病的危险性处于平均水平状态)";
		document.getElementById("nl_status_container").style.display = "block";	
		return;
	}
	
	if(over_width>-0.2&&over_width<0.2){
		if(over_width>-0.2&&over_width<0){
			document.getElementById("nl_status").innerHTML = "体重过低(相关疾病发病的危险性处于低，但其它疾病危险性增加状态)";
		}else{
			document.getElementById("nl_status").innerHTML = "肥胖前期(相关疾病发病的危险性处于增加状态)";
		}
		document.getElementById("nl_status_container").style.display = "block";		
		return;
	}
	
	if(over_width<-0.2||over_width>0.2){
		if(over_width<-0.2){
			document.getElementById("nl_status").innerHTML = "体重过低(相关疾病发病的危险性处于低，但其它疾病危险性增加状态)";
		}else{
			document.getElementById("nl_status").innerHTML = "肥胖(相关疾病发病的危险性处于高发状态)";
		}
		document.getElementById("nl_status_container").style.display = "block";	
	}
}
