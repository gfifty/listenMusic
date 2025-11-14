<template>
	<view>
		<uni-list>
			<view class="tab">
				<p class="numsingle">#</p>
				<p class="nametab">音乐名</p>
				<p class="artistertab">作者</p>
			</view>
			<uni-list-item v-for="(p,index) in singleList" :key="index" @tap="jumpTo(p,index)">
				<view class="firstMusic">
					<p class="numsingle">{{index+1}}</p>
					<image :src="p.cover" mode="aspectFit" class="cover"></image>
					<p class="namesingle">{{p.musicName}}</p>
					<P class="love" @click="likeThisMusic" >❤</P>
					<p class="artistsingle">{{p.singerName}}</p>
					<p class="playButtonsingle">•••</p>
				</view>
			</uni-list-item>
		</uni-list>
		<miniPlayer></miniPlayer>
	</view>
</template>

<script setup>
	import miniPlayer from "@/components/miniPlayer/miniPlayer1.vue"
	import {onMounted,ref} from "vue";
	import { usePlayerStore } from "../../store/player";
	import {useSearchresStore} from '/store/data.js'

	const useSearchRESStore = useSearchresStore()
	const singleList = ref([])
	const ls = ref(useSearchRESStore.searchRes)


	onMounted(() => {
		uni.request({
			url: "http://localhost:8080/Music/searchMusic",
			method: "POST",
			data: {
				musicName: ls.value
			},
			success: (res) => {
				if (res.data.status == 200) {
					singleList.value = res.data.data
				}
				else{
					console.log(res.data,"没找到");
				}
			},
			fail: (err) => {
				console.log(err);
			}
		})
	})
	function jumpTo(p,index){
		const playerStore =usePlayerStore()
		playerStore.setPlaylist(singleList.value,index)
		uni.navigateTo({
		        url: `/pages/play/play`,
		    });
	}
	
	function likeThisMusic(){
		
	}

</script>

<style>
	.firstMusic {
	  display: flex;
	  align-items: center;
	  padding: 10rpx 0rpx;
	  border-bottom: 1px solid #f2f3f4;
	}
	
	.firstMusic>p{
		font-size: medium;
		height: 100rpx;
		line-height: 100rpx;
		display: inline-block;
	}
	.firstMusic>image{
		font-size: medium;
		height: 100rpx;
		line-height: 100rpx;
		display: inline-block;
	}
	.cover{
		width: 80rpx;
	}
	.numsingle {
		width: 40rpx;
		text-align: center;
		color: #AAAAAA;
		display: inline-block;
		margin: 0 20rpx 0 5rpx;
	}
	.namesingle{
		display: inline-block;
		width: 240rpx;
		margin:0 20rpx 0 20rpx;
		color: #555555;
		font-weight: bolder;
		overflow-x: hidden;
		white-space: nowrap;
		text-overflow: ellipsis;
	}
	.artistsingle{
		display: inline-block;
		width: 200rpx;
		color: #555555;
		font-weight: bolder;
		overflow: hidden;
		white-space: nowrap;
		text-overflow: ellipsis;
	}
	.love{
		color:#aaaaaa;
		margin: 0 20rpx 0 0;
	}
	.playButtonsingle{
		margin-left: 20rpx;
	}
	.tab{
		display: flex;
	}
	.nametab{
		margin: 0 0 0 140rpx;
		width: 240rpx;
		color: #555555;
		font-weight: bolder;
	}
	.artistertab{
		margin: 0 0 0 60rpx;
		color: #555555;
		font-weight: bolder;
	}
</style>