import request from '@/utils/request'

//Get course list
export function lessonList(data) {
 return request({
url: '/user/lesson/list',
 method: 'post',
 data
 })
}

//Get course detail
export function getLessonDetail(data, lessonName) {
 return request({
 url: '/user/lesson/detail/' + lessonName,
 method: 'post',
 data
 })
}

// Add course
export function addLesson(data) {
 return request({
 url: '/user/lesson/add',
 method: 'post',
 data
 })
}

// Update course
export function updateLesson(data) {
 return request({
 url: '/user/lesson/update',
  method: 'post',
 data
 })
}

// Delete course
export function deleteLesson(id) {
 return request({
 url: '/user/lesson/delete',
 method: 'post',
 params: {
 id
 }
 })
}

// Batch delete course
export function batchDeleteLesson(data) {
 return request({
 url: '/user/lesson/delete/batch',
 method: 'post',
 data
 })
}
