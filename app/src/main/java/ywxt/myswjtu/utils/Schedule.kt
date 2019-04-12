package ywxt.myswjtu.utils

object TimetableSchedule {
    fun getTime(lesson:Int):Time{
        return when(lesson){
            1-> Time(8,0,0)
            2-> Time(8,50,0)
            3-> Time(9,50,0)
            4-> Time(10,40,0)
            5-> Time(11,30,0)
            6-> Time(14,0,0)
            7-> Time(14,50,0)
            8-> Time(15,50,0)
            9-> Time(16,40,0)
            10->Time(17,30,0)
            11-> Time(19,30,0)
            12-> Time(20,20,0)
            13-> Time(21,10,0)
            else -> Time(8,0,0)
        }
    }
}