import request from '@/utils/request'

//Get courseMenu list
export function CourseList(data, category) {
  return request({
    url: '/course/' + category,
    method: 'post',
    data
  })
}

export function getCourseDetail(data, CourseName) {
  return request({
    url: '/course/lesson/' + CourseName,
    method: 'post',
    data
  })
}
