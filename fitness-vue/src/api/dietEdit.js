import request from '@/utils/request'

//Get diet list
export function dietList(data) {
  return request({
    url: '/user/diet/list',
    method: 'post',
    data
  })
}

//Get diet detail
export function getDietDetail(data, dietName) {
  return request({
    url: '/user/diet/detail/' + dietName,
    method: 'post',
    data
  })
}

// Add diet
export function addDiet(data) {
  return request({
    url: '/user/diet/add',
    method: 'post',
    data
  })
}

// Update diet
export function updateDiet(data) {
  return request({
    url: '/user/diet/update',
    method: 'post',
    data
  })
}

// Delete diet
export function deleteDiet(id) {
  return request({
    url: '/user/diet/delete',
    method: 'post',
    params: {
      id
    }
  })
}

// Batch delete diet
export function batchDeleteDiet(data) {
  return request({
    url: '/user/diet/delete/batch',
    method: 'post',
    data
  })
}
