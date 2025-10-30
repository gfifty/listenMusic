import {defineStore} from 'pinia';

export const useUserResStore =defineStore('user', {
	// 定义状态（参数，变量）
	state:()=>{
		return { 
			userRes : {},
			isLogined : false,
			}
	},
	getters:{
		
	},
	// 动作支持异步
	actions:{
		setUserRes(Vi){
			this.userRes = Vi;
		},
		setIsLogined(Vi){
			this.isLogined = Vi;
		}
		,
		//单属性添加
		setUserResField(key, value) {
		  // 检查 userRes 是否已经存在
		  if (this.userRes) {
		    // 如果 userRes 已存在，直接为其添加/更新属性
		    this.userRes[key] = value;
		  } else {
		    // 如果 userRes 为空（null 或 undefined），则初始化为一个新的对象，并添加对应属性
		    this.userRes = { [key]: value };
		  }
		}
		}
})