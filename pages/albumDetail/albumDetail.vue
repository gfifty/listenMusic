<template>
	<view class="tit">
		<view class="ImageBox">
			<image :src="alubum.albumHead" mode="scaleToFill" class="singlistimage"></image>
		</view>
		<text class="typetitle titlename">{{alubum.albumName}}</text>
		<view class="typetitle intro">简介：{{alubum.albumIntroduction}}</view>
	</view>
	<view class="billboard">
		<view class="leftbo">
			<uni-list class="lists">
				<uni-list-item v-for="(p,index) in alubumList" :key="index">
					<view class="onemusic" @click="jumpTo(p,index)">
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
	import {usePlayerStore} from '@/store/player';
	import {storeToRefs} from 'pinia';
	import {ref} from 'vue';
	import {onLoad} from "@dcloudio/uni-app"

	const player = usePlayerStore();
	const {
		currentTrack,
		playing,
		progress,
		duration,
		lyrics,
		currentLyricIndex,
		showPlaylist,
		mode
	} = storeToRefs(player)
	const alubum = ref()
	const alubumList = ref({})

	onLoad((option) => {
		// 获取歌曲列表
		uni.request({
			url: "http://localhost:8080/albumSingle/getAlbumSingle",
			method: "POST",
			data: {
				albumId: option.albumId
			},
			success: (res) => {
				if (res.data.status == 200) {
					alubumList.value = res.data.data
					console.log(alubumList.value);
				} else {
					console.log("获取歌曲列表失败，status不为200");
				}
			},
			fail: (err) => {
				console.log(err);
			}
		})
		// 获取歌单信息（异步请求）
		fetchSinglistData(option);
	})
	// 异步函数获取歌单信息
	const fetchSinglistData = async (option) => {
				try {
					const alubumListRes = await new Promise((resolve, reject) => {
						uni.request({
							url: "http://localhost:8080/album/getAlbumThing",
							method: "POST",
							data: {
								albumId: option.albumId
							},
							success: (res) => {
								resolve(res)
							},
							fail: (err) => {
								reject(err)
							}
						});
					});

					if (alubumListRes.data.status === 200) {
						alubum.value = alubumListRes.data.data
					} else {
						console.log("获取歌单信息失败，status不为200")
					}
				} catch (err) {
					console.error("请求失败：", err)
				}}

				function jumpTo(p, index) {
					const playerStore = usePlayerStore()
					playerStore.setPlaylist(alubumList.value, index)
					uni.navigateTo({
						url: `/pages/play/play`,
					});
				}
</script>

<style>
	.tit {
			background: rgba(117, 117, 117,0.2);
			height: 400rpx;
		}
	
		.ImageBox {
			padding: 50rpx;
		}
	
		.singlistimage {
			width: 300rpx;
			height: 300rpx;
			border-radius: 20%;
		}
		.intro {
			position: relative;
			margin-top: 20rpx;
			font-size: 30rpx;
			width: 300rpx;
			height: 200rpx;
			overflow: hidden;
			text-overflow: ellipsis;
			display: -webkit-box;
			-webkit-box-orient: vertical;
			-webkit-line-clamp: 3;
			white-space: normal; 
		}
		.titlename{
			position: relative;
			width: 300rpx;
			overflow: hidden;
			text-overflow: ellipsis;
			white-space: nowrap; 
			font-weight: bold;
			left: 400rpx;
			top: -300rpx; 
			display: block;
		}
	
		.typetitle {
			position: relative;
			width: 300rpx;
			overflow: hidden;
			text-overflow: ellipsis;
			font-weight: bold;
			left: 400rpx;
			top: -300rpx; 
			display: block;
		}
	
		.lists :nth-child(1)>.onemusic>.num {
			color: red;
		}
	
		.lists :nth-child(2)>.onemusic>.num {
			color: orange;
		}
	
		.lists :nth-child(3)>.onemusic>.num {
			color: green;
		}
	
		.leftbo {
			text-align: left;
			width: 90%;
			padding: 20rpx;
			margin: 20rpx 20rpx 20rpx 0;
		}
	
		.onemusic {
			height: 50rpx;
			line-height: 70rpx;
			padding-bottom: 18rpx;
		}
	
		.num {
			display: inline-block;
			width: 30rpx;
			line-height: 30rpx;
			font-size: 30rpx;
			font-weight: 700;
			margin:0 10rpx 0 30rpx;
		}
	
		.name {
			display: inline-block;
			line-height: 30rpx;
			margin-top: 10rpx;
			font-size: 25rpx;
			width: 50%;
			overflow: hidden;
			white-space: nowrap;
			text-overflow: ellipsis;
		}
	
		.artist {
			display: inline-block;
			width: 20%;
			margin: 0 20rpx 0 20rpx;
			line-height: 24rpx;
			font-size: 20rpx;
			overflow: hidden;
			white-space: nowrap;
			text-overflow: ellipsis;
		}
	
		.playButton {
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
	
		.title {
			text-align: center;
			font-size: 40rpx;
		}
</style>