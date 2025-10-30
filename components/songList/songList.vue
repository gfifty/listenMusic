<template>
	<view class="billboard">
		<view class="leftbo">
			<p class="title">飙升榜</p>
			<uni-list class="lists">
				<uni-list-item  v-for="(p,index) in originalList" :key="index"> 
					<view class="onemusic" @click="jumpTo(p,index)" >
						<text class="num">{{index+1}}</text>
						<text class="name">{{p.musicName}}</text>
						<text class="artist">{{p.singerName}}</text>
						<text class="playButton" >▶</text>
					</view>
				</uni-list-item>
			</uni-list>
		</view>
	</view>
</template>

<script setup>
	import {req} from "/hook/req.js"
	import {ref} from "vue"
	
	const originalList=ref([])
	req("http://localhost:8080/Music/hotMusic").then(result=>{
		if(result.data.status == 200){
			originalList.value=result.data.data
		}                                                        
	})
	function jumpTo(p,index){
		uni.navigateTo({
		        url: `/pages/play/play?singlist=${encodeURIComponent(JSON.stringify(originalList.value))}&music=${encodeURIComponent(JSON.stringify(p))}&index=${index}`,
		    });
	}
</script>

<style>
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
	border-bottom: 1px solid #797979;
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