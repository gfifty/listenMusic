<template>
	<view>
		<uni-list>
			<uni-list-item v-for="(p,index) in singleList" :key="index">
				<view class="firstMusic">
					<image :src="p.playlist_cover" mode="aspectFit" class="cover"></image>
					<text class="namesingle">{{p.Name}}</text>
					<text class="artistsingle">{{p.User_name}}</text>
				</view>
			</uni-list-item>
		</uni-list>
	</view>
</template>

<script setup>
import {
		onMounted,
		ref
	} from "vue";
	import {useSearchresStore} from '/store/data.js'

	const useSearchRESStore = useSearchresStore()
	const singleList = ref([])
	const ls = ref(useSearchRESStore.searchRes)


	onMounted(() => {
		uni.request({
			url: "http://localhost:8080/Search/singlist",
			method: "POST",
			data: {
				name: "Re"
			},
			success: (res) => {
				if (res.data.code == 200) {
					singleList.value = res.data.data
				}
				else{
					console.log(res.data,"没找到");
				}
			},
			default: (res => {
				console.log("失败了");
			})
		})
	})
	
	function likeThisMusic(){
		console.log("占位");
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
		margin-left: 20rpx;
	}
	.numsingle {
		width: 40rpx;
		text-align: center;
		color: #AAAAAA;
		display: inline-block;
		margin: 0 20rpx 0 5rpx;
	}
	.namesingle{
		display: inline;
		width: 370rpx;
		margin:0 20rpx 0 20rpx;
		color: #555555;
		font-weight: bolder;
		overflow-x: hidden;
		white-space: nowrap;
		text-overflow: ellipsis;
		font-size: small;
	}
	.artistsingle{
		display: inline;
		width: 200rpx;
		color: #555555;
		font-weight: bolder;
		font-size: smaller;
		overflow: hidden;
		white-space: nowrap;
		text-overflow: ellipsis;
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

</style>