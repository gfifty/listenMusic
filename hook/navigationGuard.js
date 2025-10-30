export function handleTabBarTap(targetUrl) {
  const isLoggedIn = uni.getStorageSync('isLoggedIn'); // 检查登录状态
  if (!isLoggedIn && targetUrl === '/pages/user/user') {
    uni.showToast({
      title: '请先登录',
      icon: 'none',
    });
    return false; // 阻止跳转
  }
  return true; // 允许跳转
}