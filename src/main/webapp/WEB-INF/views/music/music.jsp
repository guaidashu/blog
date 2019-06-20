<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Music</title>
    <link rel="stylesheet" href="/static/css/body.css"/>
    <link rel="stylesheet" href="/static/bootstrap/css/bootstrap.min.css"/>
    <script type="text/javascript" src="/static/js/jquery.js"></script>
    <script type="text/javascript" src="/static/bootstrap/js/bootstrap.min.js"></script>
    <link type="text/css" rel="stylesheet" href="/static/css/common.css"/>
</head>
<body>
<div class="main_container">
    <div class="header_break_container">
        <div class="header_break">
            <a href="/"><img src="/static/images/index/logo.png"></a>
        </div>
        <div class="header_nav">
            <a href="/choice/choice" style="color: #ffffff;">
                <div class="header_nav_btn ">FM</div>
            </a>
            <a href="/play/play">
                <div class="header_nav_btn header_nav_btn_active" style="margin-left:20px;">Music</div>
            </a>
            <div style="clear: both;"></div>
        </div>
    </div>
    <div class="common_remind_container">
        <div style="padding: 15px 0;">
            <img src="/static/images/assets/headset_ic.svg">
        </div>
        <div style="height: 1px; background-color: #ffffff; width: 180px; margin: 0 auto;"></div>
        <div class="common_remind_text">
            If you don't like the road you're walking, start paving
            <br/>
            another one.
        </div>
    </div>
    <div class="fm_music_container">
        <div class="mp-pro">
            <div class="mp-pro-current"></div>
        </div>
        <div class="fm_music_main_container">
            <div class="mp-box"
                 style="width: 100%; height: 12px; line-height: 12px; font-family: DINAlternate-Bold,serif; color: #ffffff; opacity: 0.6; font-size: 12px;">
                <div class="mp-info" style="overflow:hidden;">
                    <span class="mp-name"></span>&nbsp; - &nbsp;
                    <span class="mp-singer"></span>
                </div>
                <img style="display: none;" src="" class="mp-cover">
            </div>
            <div style="width: 100%;">
                {{/*                按钮组*/}}
                <div class="play_btn_container">
                    <div class="mp-prev" title="上一首" style="float: left; position: relative; width: 60px;">
                        <img src="/static/images/assets/last_btn.svg"
                             style="position:relative; top: 20px; cursor:pointer;">
                    </div>
                    <div class="mp-pause" title="播放" style="float: left; position: relative; width: 80px;">
                        <img src="/static/images/assets/play_btn.svg"
                             style="position:relative; top: 8px; cursor:pointer;">
                    </div>
                    <div class="mp-next" title="下一首" style="float: left; position: relative; width: 60px;">
                        <img src="/static/images/assets/next_btn.svg"
                             style="position:relative; top: 20px; cursor:pointer;">
                    </div>
                    <div class="mp-mode" title="" style="float: left; position: relative; top: 2px; width: 60px;">
                        <img src="/static/images/assets/sequence_btn.svg"
                             style="position:relative; top: 10px; cursor:pointer;">
                    </div>
                    <div style="clear: both"></div>
                </div>
                {{/*                时间按钮组*/}}
                <div class="fm_time_btn_container">
                    <div class="fm_time_btn fm_time_btn_active" style="margin-left: 0;">
                        30 min
                    </div>
                    <div class="fm_time_btn">
                        1 hour
                    </div>
                    <div class="fm_time_btn">
                        2 hours
                    </div>
                    <div class="fm_time_btn">
                        <img src="/static/images/assets/unlimited_btn.svg"/>
                    </div>
                    <div style="clear: both;"></div>
                </div>
                <div style="float:right;">
                    {{/*                列表按钮*/}}
                    <div class="fm_music_list_container mp-menu">
                        <img src="/static/images/assets/playlist_btn.svg" class="mp-list-toggle"/>
                    </div>
                    {{/*                声音*/}}
                    <div class="mp-vol">
                        <div class="mp-vol-img">
                            <img src="/static/images/assets/voice_btn.svg"/>
                        </div>
                        <div class="mp-vol-range" data-range_min="0" data-range_max="100" data-cur_min="80">
                            <div class="mp-vol-current" style="float: left;"></div>
                            <div class="mp-vol-circle" tabindex="0" style="float: left;"></div>
                        </div>
                        <div style="clear: both"></div>
                    </div>
                </div>
                <div style="clear: both"></div>
            </div>
        </div>
    </div>

    <div class="mp-list-box">
        <div class="mp-list-title-container">
            <div style="font-size: 12px;color: rgba(255,255,255,0.40);letter-spacing: 0; line-height: 25px; padding-left: 15px; padding-top: 10px; padding-bottom: 5px;">
                Music
                type
            </div>
            <ul class="mp-list-title" style="list-style: none;"></ul>
        </div>
        <div class="mp-list-table-container">
            <div class="mp-list-introduce-container">
                <!-- 当前分类的名字 -->
                <div class="mp-list-category">
                    My music List
                </div>
                <div class="mp-list-song-num">
                    All 128 Songs
                </div>
            </div>
            <div class="mp-list-table-container-child">
                <table class="mp-list-table">
                    <thead style="border-bottom: 1px solid rgba(0,0,0,0.06);line-height:30px; font-size:12px; font-family: DINAlternate-Bold,serif; color: rgba(255,255,255,0.50);">
                    <tr>
                        <th class="fm_music_song_no">No.</th>
                        <th class="fm_music_song_name">Songs</th>
                        <th class="fm_music_song_category">Category</th>
                        <th class="fm_music_song_singer">Singer</th>
                        <th class="fm_music_song_time">Time</th>
                        <th class="fm_music_song_operate">Operate</th>
                    </tr>
                    </thead>
                    <tbody class="mp-list"></tbody>
                </table>
                <div class="mp-page"></div>
            </div>
        </div>
        <div style="clear: both;"></div>
    </div>
</div>
</body>

<script type="text/javascript" src="/static/plugin/mplayer-master/js/mplayer.js"></script>
<script type="text/javascript" src="/static/plugin/mplayer-master/js/mplayer-list.js"></script>
<script type="text/javascript" src="/static/plugin/mplayer-master/js/mplayer-functions.js"></script>
<script type="text/javascript" src="/static/plugin/mplayer-master/js/jquery.nstSlider.js"></script>

<script type="text/javascript">
    var modeText = ['顺序播放', '单曲循环', '随机播放', '列表循环'];
    var modeImg = ['/static/images/assets/sequence_btn.svg', '/static/images/assets/single_cycle_btn.svg', '/static/images/assets/random_btn.svg', '/static/images/assets/order_btn.svg'];
    var player = new MPlayer({
        // 容器选择器名称
        containerSelector: '.main_container',
        // 播放列表
        songList: mplayer_song,
        // 专辑图片错误时显示的图片
        defaultImg: 'img/mplayer_error.png',
        // 自动播放
        autoPlay: false,
        // 播放模式(0->顺序播放,1->单曲循环,2->随机播放,3->列表循环(默认))
        playMode: 0,
        playList: 0,
        playSong: 0,
        // 当前歌词距离顶部的距离
        lrcTopPos: 34,
        // 列表模板，用${变量名}$插入模板变量
        listFormat: '<tr><td class="fm_music_song_no">${no}$</td><td class="fm_music_song_name"><span>${name}$</span></td><td class="fm_music_song_category"><span>${category}$</span></td><td class="fm_music_song_singer"><span>${singer}$</span></td><td class="fm_music_song_time">${time}$</td><td class="fm_music_song_operate"><img style="cursor:pointer;" class="fm_music_song_operate_btn" src="/static/images/assets/more_btn.svg" /><div class="fm_music_song_operate_pop"><div class="fm_music_play_btn"><img src="/static/images/assets/delete_ic.svg">&nbsp;Play</div><div class="fm_music_copy_btn" style="border-bottom: none;"><img src="/static/images/assets/copy_ic.svg">&nbsp;Copy URL</div></div></td></tr>',
        // 音量滑块改变事件名称
        volSlideEventName: 'change',
        // 初始音量
        defaultVolume: 80
    }, function () {
        // 绑定事件
        this.on('afterInit', function () {
            console.log('播放器初始化完成，正在准备播放');
        }).on('beforePlay', function () {
            var $this = this;
            var song = $this.getCurrentSong(true);
            var songName = song.name + ' - ' + song.singer;
            console.log('即将播放' + songName + '，return false;可以取消播放');
        }).on('timeUpdate', function () {
            var $this = this;
            console.log('当前歌词：' + $this.getLrc());
        }).on('end', function () {
            var $this = this;
            var song = $this.getCurrentSong(true);
            var songName = song.name + ' - ' + song.singer;
            console.log(songName + '播放完毕，return false;可以取消播放下一曲');
        }).on('mute', function () {
            var status = this.getIsMuted() ? '已静音' : '未静音';
            console.log('当前静音状态：' + status);
        }).on('changeMode', function () {
            var $this = this;
            // 获取当前的播放模式枚举状态码
            let modeNum = $this.getPlayMode();
            // 切换标题
            var mode = modeText[modeNum];
            $this.dom.container.find('.mp-mode').attr('title', mode);
            // 切换图片
            $this.dom.container.find('.mp-mode').html('<img src="' + modeImg[modeNum] + '" style="position:relative; top: 10px; cursor:pointer;">');
            console.log('播放模式已切换为：' + mode);
        });
    });

    $(document.body).append(player.audio); // 测试用

    setEffects(player);

</script>

<script type="text/javascript">
    (function ($) {
        let music_function = function () {
            let self = this;
            this.body = $(document.body);
            this.body.keydown(function (e) {
                // 按下空格就暂停, 已经暂停则播放
                if (e.keyCode == 32) {
                    if (player.audio.prop('paused')) {
                        player.play();
                    } else {
                        player.pause();
                    }
                }
            });
            this.body.delegate(".fm_music_song_operate_btn", "click", function () {
                self.showLittlePop($(this));
            });
            this.body.delegate(".fm_music_play_btn", "click", function () {
                $(this).parent().parent().parent().dblclick();
                self.showLittlePop($(this).parent());
            });
        };
        music_function.prototype = {
            test: function () {
                alert("test");
            },
            showLittlePop(obj) {
                obj = obj.parent().children().eq(1);
                if (obj.hasClass("fm_music_song_operate_pop_show")) {
                    obj.removeClass("fm_music_song_operate_pop_show");
                } else {
                    $(".fm_music_song_operate_pop_show").removeClass("fm_music_song_operate_pop_show");
                    obj.addClass("fm_music_song_operate_pop_show");
                }
            }
        }
        window['music_function'] = music_function
    })(jQuery);
    $(function () {
        let music = new music_function();
    });
</script>

</html>

<!-- designed by yy(github.com/guaidashu) -->
