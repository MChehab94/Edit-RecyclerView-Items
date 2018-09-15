package mchehab.com.advancedrecyclerview.click_listeners

import mchehab.com.advancedrecyclerview.Person

interface OnEditListener {
    fun editItem(person: Person, index: Int)
}