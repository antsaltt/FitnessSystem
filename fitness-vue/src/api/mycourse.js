import request from '@/utils/request'

export function mycourseNumber(data, userId) {
  return request({
    url: '/mycourse/mycourseNumber/' + userId,
    method: 'post',
    data
  })
}

export function mycourseList(data) {
  return request({
    url: '/mycourse/list',
    method: 'post',
    data
  })
}

export function mycourseNewList(data) {
  return request({
    url: '/mycourseNew/list',
    method: 'post',
    data
  })
}

export function addMycourse(data) {
  return request({
    url: '/mycourse/add',
    method: 'post',
    data
  })
}

export function updateMycourse(data) {
  return request({
    url: '/mycourse/update',
    method: 'post',
    data
  })
}

export function deleteMycourse(id) {
  return request({
    url: '/mycourse/delete',
    method: 'post',
    params: {
      id
    }
  })
}

export function batchDeleteMycourse(data) {
  return request({
    url: '/mycourse/delete/batch',
    method: 'post',
    data
  })
}




