<template>
	<view class="tit">
	<image :src="singlist.singlistHead" mode="scaleToFill" class="singlistimage"></image>
	<text class="typetitle">{{singlist.singlistName}}</text>
	<text class="typetitle intro">简介：{{singlist.singlistIntroduction}}</text>
	</view>
	<view class="billboard">
		<view class="leftbo">
			<uni-list class="lists">
				<uni-list-item  v-for="(p,index) in singlistMusic" :key="index"> 
					<view class="onemusic"  @click="jumpTo(p,index)" >
						<text class="num">{{index+1}}</text>
						<text class="name">{{p.musicName}}</text>
						<text class="artist">{{p.singerName}}</text>
						<text class="playButton">▶</text>
					</view>
				</uni-list-item>
			</uni-list>
		</view>
	</view>
</template>

<script setup>
import { ref } from 'vue';
import {onLoad} from "@dcloudio/uni-app"
const singlist=ref({})
const singlistMusic=ref([])

// 获取歌曲列表（同步请求）
onLoad((option) => {
  // 获取歌曲列表
  uni.request({
    url: "http://localhost:8080/singlistMusic/getSinglistMusic",
    method: "POST",
    data: { singlistId: option.singlistId },
    success: (res) => {
      if (res.data.status === 200) {
        singlistMusic.value = res.data.data;
        console.log(singlistMusic.value);
      } else {
        console.log("获取歌曲列表失败，status不为200");
      }
    },
    fail: (err) => {
      console.log("请求失败:", err);
    },
  });

  // 获取歌单信息（异步请求）
  fetchSinglistData(option);
});

// 异步函数获取歌单信息
const fetchSinglistData = async (option) => {
  try {
    const singlistRes = await new Promise((resolve, reject) => {
      uni.request({
        url: "http://localhost:8080/singlistMusic/getSinglistThing",
        method: "POST",
        data: { singlistId: option.singlistId },
        success: (response) => resolve(response),
        fail: (error) => reject(error),
      });
    });

    if (singlistRes.data.status === 200) {
      singlist.value = singlistRes.data.data;
    } else {
      console.log("获取歌单信息失败，status不为200");
    }

  } catch (err) {
    console.error("请求失败:", err);
  }
};
	function jumpTo(p,index){
		uni.navigateTo({
		        url: `/pages/play/play?singlist=${encodeURIComponent(JSON.stringify(singlistMusic.value))}&music=${encodeURIComponent(JSON.stringify(p))}&index=${index}`,
		    });
	}
</script>

<style>
	.tit{
		background-color: #797979;
		height: 400rpx;
	}
.singlistimage{
	width: 400rpx;
	height: 400rpx;
	}
	.intro{
		margin-top: 20rpx;
		font-size: 30rpx;
		width: 300rpx;
		overflow: hidden;
		  text-overflow: ellipsis;
		  display: -webkit-box;
		  -webkit-box-orient: vertical;
		  -webkit-line-clamp: 3;
	}
.typetitle{
	position: relative;
	left: 400rpx;
	top: -300rpx;
	display: block;
}
.lists :nth-child(1)>.onemusic>.num{
		color: red;
	}
	.lists :nth-child(2)>.onemusic>.num{
		color: orange;
	}
	.lists :nth-child(3)>.onemusic>.num{
		color: green;
	}
	.leftbo{
		text-align: center;
		border: 1px solid #aaaaaa;
		width: 90%;
		padding: 20rpx;
		margin: 20rpx 20rpx 20rpx 0;
	}

	.onemusic{
	height: 30rpx;
	line-height: 30rpx;
	/* border-bottom: 1px solid #797979; */
	padding-bottom: 18rpx;
	}
	.num{
	display: inline-block;
	width: 30rpx;
	line-height: 30rpx;
	font-size: 30rpx;
	font-weight: 700;
	margin-right: 10rpx;
	}
	.name{
	display: inline-block;
	line-height: 30rpx;
	margin-top: 10rpx;
	font-size: 25rpx;
	width: 46%;
	overflow: hidden;
	white-space: nowrap;
	text-overflow: ellipsis;
	}
	.artist{
	display: inline-block;
	width: 30%;
	margin-left: 10rpx;
	line-height: 24rpx;
	font-size: 20rpx;
	overflow: hidden;
	white-space: nowrap;
	text-overflow: ellipsis;
	}
	.playButton{
	display: inline-block;
	margin-left: 17rpx;
	line-height: 30rpx;
	width: 30rpx;
	height: 30rpx;
	font-size: 25rpx;
	border: 1px solid black;
	border-radius: 30rpx;
	text-align: center;
	padding-left: 3rpx;
	padding-bottom: 1rpx;
	}
	.title{
	text-align: center;
	font-size: 40rpx;
	}
</style>
