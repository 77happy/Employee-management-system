function test(params) { //面签页面--根据apid查询已有信息
    return service({
        url: '/user/login',
        method: 'post',
		data: JSON.stringify(params)
    })
}

function queryall(params) { //面签页面--根据apid查询已有信息
    return service({
        url: '/getUsers',
        method: 'get',//get方法的参数直接暴露就好了，不用放在data里面也不用转成json
		params
    })
}
function insert(params) { //面签页面--根据apid查询已有信息
    return service({
        url: '/add',
        method: 'post',
		data: JSON.stringify(params)
    })
}
function del(params) { //面签页面--根据apid查询已有信息
    return service({
        url: '/delete/'+params.id,
        method: 'delete',//delte方法是删除资源 一般都不用的，要么get要么post 告知后端你要删除id为多少的数据 ?
		
    })
}