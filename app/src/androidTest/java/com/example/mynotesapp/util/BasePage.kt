package com.example.mynotesapp.util

import com.example.mynotesapp.R
import com.example.mynotesapp.R.id.*
import com.example.mynotesapp.util.BaseTest

abstract class BasePage : BaseTest() {

    protected val firstPosition: Int = 0
    protected val deleteIconButton = idIVDelete
    protected val mainRecyclerView = notesRV
    protected val titleEdit = idEdtNoteName
    protected val descriptionEdit = idEdtNoteDesc
    protected val cancel = idCancelButton
    protected val save = idBtn
    protected val update = idBtn
    protected val testTitleText = "Test_Title_01"
    protected val testDescriptionText = "Test_Description_01"
    protected val colorWhite = R.color.white
    protected val colorLightGreen = R.color.light_green
    protected val colorBabyBlue = R.color.baby_blue
    protected val colorViolet = R.color.violet
    protected val colorDarkGrey = R.color.dark_grey
    protected val colorGray = R.color.gray
    protected val colorRedOrange = R.color.red_orange
    protected val colorRedPink = R.color.red_pink

}