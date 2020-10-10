function test(params) { //面签页面--根据apid查询已有信息
    return service({
        url: 'testlogin',
        method: 'post',
	data: JSON.stringify(params)
    })
}