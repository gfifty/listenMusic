<template>
	<view>
		<view class="">
		<h4 class="hothead">热门歌单</h4>
	</view>
		<hr  class="splitline" />
		<view class="listBar">
			<view  class="list"  v-for="(p,index) in albumList" :key="index" @click="openSinglist(p.singlistId)">
				<image mode="aspectFill" class="coverImage" :src="p.singlistHead"></image>
				<p class="coverText">{{p.singlistName}}</p>
				<p class="coverText">{{p.album_num}}首</p>
				<p class="coverText">{{p.singlistTime}}</p>
			</view>
		
		</view>
	</view>
</template>

<script setup>
	import {req} from "/hook/req.js"
	import { inject,ref } from "vue";
	import songList from "/components/songList/songList.vue"
	
	const $root = inject('$root')
	const albumList = ref([])
	
	req("http://localhost:8080/singlist/getHotList").then(result=>{
		if(result.data.status==200){
			albumList.value=result.data.data
		}
	})
	function openSinglist(singlistId){
		uni.navigateTo({
			url: `/pages/singlistDetail/singlistDetail?singlistId=${singlistId}`
		});
	}

</script>

<style>
	.hothead{
		font-size: 36rpx;
		margin-left: 50rpx;
		margin-top: 46rpx;
		margin-bottom: 23rpx;
		display: inline-block;
	}
	.more{
		font-size: 36rpx;
		margin-left: 420rpx;
		display: inline-block;
	}
	.splitline{
		border: 1rpx solid #CAF982;
		width: 792rpx;
		text-align: center;
	}

	.list{
		margin-left: 54rpx;
		display: inline-block;
	}
	.listBar{
		display: inline-block;
	}
	.coverImage{
		width: 177rpx;
		height: 177rpx;
		margin-top: 23rpx ;
		border: 1px solid black;
	}
	.coverText{
		font-size: 18rpx;
		width: 160rpx;
		overflow : hidden;
		white-space: nowrap;
		text-overflow: ellipsis;
	}
	
</style>