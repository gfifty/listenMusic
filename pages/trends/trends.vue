<template>
	<view>
		<uni-list>
			<view class="tab">
				<p class="numsingle">#</p>
				<p class="nametab">音乐名</p>
				<p class="artistertab">作者</p>
			</view>
			<uni-list-item v-for="(p,index) in singleList" :key="index">
				<view class="firstMusic">
					<p class="numsingle">{{index+1}}</p>
					<image :src="p.cover" mode="aspectFit" class="cover"></image>
					<text class="namesingle">{{p.musicName}}</text>
					<text class="artistsingle">{{p.singerName}}</text>
					<text class="playButtonsingle">•••</text>
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
	import {useUserResStore} from  "../../store/logindata.js"
	import { onTabItemTap } from "@dcloudio/uni-app"
	const userResStore =useUserResStore()
	const singleList = ref([])


	onMounted(() => {
		uni.request({
			url: "http://localhost:8080/history/getHistory",
			method: "POST",
			data: {
				    userId:userResStore.userRes.userId
			},
			success: (res) => {
				if (res.data.status == 200) {
					singleList.value = res.data.data
				}
				else{
					console.log("没找到");
				}
			},
			default: (res => {
				console.log("失败了");
			})
		})
	})
	// 点击我的Tabbar的判断
	onTabItemTap((e)=>{
		if (!userResStore.isLogined) {
			uni.redirectTo({
				url: '/pages/login/login'
			});
			uni.showToast({
				title:"请先进行登录",
				icon:"none"
			})
		}
	})
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
		font-size: 25rpx;
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
		font-size: 23rpx;
	}
	.love{
		color:#aaaaaa;
		margin: 0 20rpx 0 0;
	}
	.playButtonsingle{
		margin-left: 20rpx;
		font-size: small;
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
		margin: 0 0 0 40rpx;
		color: #555555;
		font-weight: bolder;
	}
</style>