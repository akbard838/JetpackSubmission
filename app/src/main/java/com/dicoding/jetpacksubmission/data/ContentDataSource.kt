//package com.dicoding.jetpacksubmission.data
//
//import androidx.lifecycle.LiveData
//import androidx.paging.PagedList
//
//interface ContentDataSource {
//
//    fun getMovies(): LiveData<Resource<PagedList<CourseEntity>>>
//
//    fun getCourseWithModules(courseId: String): LiveData<Resource<CourseWithModule>>
//
//    fun getAllModulesByCourse(courseId: String): LiveData<Resource<List<ModuleEntity>>>
//
//    fun getContent(moduleId: String): LiveData<Resource<ModuleEntity>>
//
//    fun getBookmarkedCourses(): LiveData<PagedList<CourseEntity>>
//
//    fun setCourseBookmark(course: CourseEntity, state: Boolean)
//
//    fun setReadModule(module: ModuleEntity)
//
//}