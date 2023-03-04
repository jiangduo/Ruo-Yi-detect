import request from '@/utils/request'

// 查询测试文件列表
export function listFile(query) {
  return request({
    url: '/detect/file/list',
    method: 'get',
    params: query
  })
}

// 查询测试文件详细
export function getFile(fileId) {
  return request({
    url: '/detect/file/' + fileId,
    method: 'get'
  })
}

// 新增测试文件
export function addFile(data) {
  return request({
    url: '/detect/file',
    method: 'post',
    data: data
  })
}

// 修改测试文件
export function updateFile(data) {
  return request({
    url: '/detect/file',
    method: 'put',
    data: data
  })
}

// 删除测试文件
export function delFile(fileId) {
  return request({
    url: '/detect/file/' + fileId,
    method: 'delete'
  })
}
