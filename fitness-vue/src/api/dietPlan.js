import request from '@/utils/request'

//Get dietPlan list
export function dietPlanList(data, category) {
  return request({
    url: '/plan/' + category,
    method: 'post',
    data
  })
}

export function getDietDetail(data, dietName) {
  return request({
    url: '/plan/diet/' + dietName,
    method: 'post',
    data
  })
}
