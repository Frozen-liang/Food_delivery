function loginApi(data) {
  return $axios({
    'url': '/logins/login',
    'method': 'post',
    data
  })
}

function logoutApi(){
  return $axios({
    'url': '/logins/logout',
    'method': 'post',
  })
}
