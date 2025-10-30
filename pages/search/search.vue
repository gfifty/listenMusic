<template>
	<page-head></page-head>
	<searchresult :searchdata = "searchRES"></searchresult>
	<view class="changetype">
		<uni-list >
			<uni-list-item ><p class="selectres" :class="{ 'active': selectedButton === 1 }" @click="selectType('single');changeColor(1)">单曲</p></uni-list-item>
			<uni-list-item ><p class="selectres" :class="{ 'active': selectedButton === 2 }" @click="selectType('singer');changeColor(2)">歌手</p></uni-list-item>
			<uni-list-item ><p class="selectres" :class="{ 'active': selectedButton === 3 }" @click="selectType('playlist');changeColor(3)">歌单</p></uni-list-item>
			<uni-list-item ><p class="selectres" :class="{ 'active': selectedButton === 4 }" @click="selectType('album');changeColor(4)">专辑</p></uni-list-item>
		</uni-list>
		<view class="num1"> 
			<single   v-if="pageType === 'single'"></single>
			<singer   v-if="pageType === 'singer'"></singer>
			<playlist v-if="pageType === 'playlist'"></playlist>
			<album    v-if="pageType === 'album'"></album>
		</view>
	</view>
</template>

<script setup>
	import { ref , onMounted } from "vue"
	import {useSearchresStore} from "/store/data.js"
	import searchresult from "/components/searchResult/searchResult.vue"
	import single from "/components/singlePage/singlePage.vue"
	import singer from "/components/singerPage/singerPage.vue"
	import playlist from "/components/playlistPage/playlistPage.vue"
	import album from "/components/albumPage/albumPage.vue"
	import pageHead from "/components/pageHead/pageHead.vue"
	
// 使用pinia的state和actions
	const searchRESStore =useSearchresStore()
	const searchRES =ref(searchRESStore.searchRES)

	const selectedButton = ref(1)
	const pageType =ref("single")
	
// 更新searchRES
	onMounted(() =>{
		searchRES.value =searchRESStore.searchRES
	})
	
	function selectType(type){
		pageType.value = type
	}
	function changeColor(num){
		selectedButton.value =num;
	}
 </script>
 
<style>
	.selectres{
		width: 25%;
		display: inline-block;
		background-color: #F2F2F2;
		height: 90rpx;
		border: 0px solid black;
		text-align: center;
		line-height: 90rpx;
		border-radius: 0;
	}
	.active{
		background-color: #aaaaaa;
	}
</style>
