<template>
	<view>
		<view class="uni-form-item uni-column">
						<view class="title">性别选择</view>
						<radio-group class="uni-list" @change="sexChange">
							<label class="uni-list-cell uni-list-cell-pd" v-for="(item,index) in radioItems" :key="index">
								<view  class="sexbu">
									<radio :id="item.name" :value="item.name" :checked="item.checked"></radio>
								</view>
								<view class="sexbu">
									<label class="label-2-text" :for="item.name">
										<text>{{item.value}}</text>
									</label>
								</view>
							</label>
						</radio-group>
					</view>
	</view>
</template>

<script setup>
import { ref } from 'vue';
import {useUserResStore} from"../../store/logindata.js"
const  UserResStore =useUserResStore()
const radioItems=ref([{name: 0,value: '女'},{name: '1',value: '男',checked: 'true'}])

function sexChange(e){
uni.request({
	url:"http://localhost:8080/user/updateSex",
	method:"POST",
	data:{
		userId:UserResStore.userRes.userId,
		username:UserResStore.userRes.username,
		password:UserResStore.userRes.password,
		sex:e.target.value
	},
	success: (res) => {
		if (res.data.status == 200) {
			console.log("");
			uni.showToast({
				title: '修改成功',
				icon:"success"
			});
		} else{
			
		}
	}
})
	}
</script>

<style>
.sexbu{
	width: 80rpx;
	display: inline-block;
}
</style>
