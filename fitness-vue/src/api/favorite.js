import request from '@/utils/request'

export function favoriteList(data) {
  return request({
    url: '/favorite/list',
    method: 'post',
    data
  })
}

export function favoriteNewList(data) {
  return request({
    url: '/favoriteNew/list',
    method: 'post',
    data
  })
}

export function addFavorite(data) {
  return request({
    url: '/favorite/add',
    method: 'post',
    data
  })
}

export function updateFavorite(data) {
  return request({
    url: '/favorite/update',
    method: 'post',
    data
  })
}

export function deleteFavorite(id) {
  return request({
    url: '/favorite/delete',
    method: 'post',
    params: {
      id
    }
  })
}

export function batchDeleteFavorite(data) {
  return request({
    url: '/favorite/delete/batch',
    method: 'post',
    data
  })
}
