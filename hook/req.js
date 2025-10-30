// get方法
const req=(urlString)=>{
	const promise =new Promise((res,req)=>{
	uni.request({
		url:urlString,
		success(result){
			res(result)
		},
		fail(err){
		}
	})
	})
	return promise
}
//post请求
const post=(urlString ,data)=>{
	const promise =new Promise((reslove,reject)=>{
		uni.request({
			url:urlString,
			data:data,
			method:'POST',
			header:{
				'content-type':'application/json'
			},
			success(result) {
				reslove(result)
			},
			fail(err) {
				reject(err)
			}
		})
	})
	return promise
}
export {
	req,post
}