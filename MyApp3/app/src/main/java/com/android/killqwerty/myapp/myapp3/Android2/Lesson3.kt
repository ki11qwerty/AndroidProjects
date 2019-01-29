package com.android.killqwerty.myapp.myapp3.Android2

import android.content.ContentValues
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast

import com.android.killqwerty.myapp.myapp3.R

import java.util.ArrayList
import java.util.Random

 class Lesson2:AppCompatActivity() {
internal var names = arrayOf("Иванов", "Петров", "Сидоров", "Антонов", "Песков", "Никульшин", "Ивлеев", "Захаров", "Марченко", "Путин", "Медведев", "Навальный", "Дробинин", "Винярский", "Ильченко", "Крюков")
internal var i_o = arrayOf("А.А", "А.В", "Е.Г", "О.Ю", "Г.О", "П.П", "П.В", "Н.О", "Е.А", "О.Г", "В.Е", "А.Н", "А.Б", "Б.Ю")
internal var posts = arrayOf("Напальник отдела", "уборщик", "бармен", "тестировщик", "царь во дворца", "помощник директора", "системный администратор", "официант", "халдей", "повар", "шеф-повар", "администратор", "бухгалтер", "закупщик", "грузчик", "программист", "доставщик", "поставщик", "кладовщик", "старший бухгалтер", "SEO", "фотограф", "водитель", "стажер", "директор")
internal var listener:View.OnClickListener
internal var btnOpenFields:Button
internal var btnLoad:Button
internal var btnDelete:Button
internal var btnExample:Button
internal var btnAddNew:Button
internal var btnUpdate:Button
internal var btnCancel:Button
internal var btnDelete1Element:Button
internal var BtnDelete1ElementInLayout:Button
internal var btnSort:Button
internal var btnSelect:Button
internal var radioGroup:RadioGroup
internal var etFio:EditText
internal var etAge:EditText
internal var etPost:EditText
internal var etCost:EditText
internal var etIdSet:EditText
internal var etDelete1Element:EditText
internal var dbHelper:Lesson1DbHelper
internal var allPersonsLayout:LinearLayout
internal var fieldsLayout:LinearLayout
internal var delete1ElementLayout:LinearLayout
internal var radioButtonsLayout:LinearLayout
internal var persons:ArrayList<Person>


override fun onCreate(savedInstanceState:Bundle?) {
super.onCreate(savedInstanceState)
setContentView(R.layout.android2_lesson1)
setButtonsAndView()
dbHelper = Lesson1DbHelper(this, DB_NAME)
allPersonsLayout = findViewById(R.id.andr2_lesson1_linearl_in_scroll)
delete1ElementLayout = findViewById(R.id.andr2_lesson1_linear_delete_element)
fieldsLayout = findViewById(R.id.andr2_lesson1_fields_linear)
radioButtonsLayout = findViewById(R.id.andr2_lesson1_layout_with_radiogroup)
persons = ArrayList()

    fieldsLayout.visibility = View.GONE
    etIdSet.visibility = View.GONE
    delete1ElementLayout.visibility = View.GONE
    radioButtonsLayout.visibility = View.GONE
}
 fun setButtonsAndView() {
setOnClick()
 //TODO: запилить второй листенер, без использования базы данных
        btnOpenFields = findViewById(R.id.btn_a2_l1_open_fields)
btnDelete = findViewById(R.id.btn_a2_l1_delete)
btnLoad = findViewById(R.id.btn_a2_l1_load)
btnExample = findViewById(R.id.btn_a2l1_example)
btnAddNew = findViewById(R.id.andr2_lesson1_add_new)
btnUpdate = findViewById(R.id.andr2_lesson1_btn_update)
btnCancel = findViewById(R.id.andr2_lesson1_cancel)
btnDelete1Element = findViewById(R.id.andr2_lesson1_btn_delete1element)
BtnDelete1ElementInLayout = findViewById(R.id.andr2_lesson1_btn_delete1element_in_layout)
btnSort = findViewById(R.id.andr2_lesson1_btn_sort)
btnSelect = findViewById(R.id.android2_lesson1_btn_select)
radioGroup = findViewById(R.id.andr2_lesson1_radiogroup1)

btnOpenFields.setOnClickListener(listener)
btnDelete.setOnClickListener(listener)
btnLoad.setOnClickListener(listener)
btnExample.setOnClickListener(listener)
btnAddNew.setOnClickListener(listener)
btnUpdate.setOnClickListener(listener)
btnCancel.setOnClickListener(listener)
btnDelete1Element.setOnClickListener(listener)
BtnDelete1ElementInLayout.setOnClickListener(listener)
btnSort.setOnClickListener(listener)
btnSelect.setOnClickListener(listener)

etFio = findViewById(R.id.a2l1_et_f_i_o)
etAge = findViewById(R.id.a2l1_et_age)
etPost = findViewById(R.id.a2l1_et_post)
etCost = findViewById(R.id.a2l1_et_cost)
etIdSet = findViewById(R.id.a2l1_et_set_id)
etDelete1Element = findViewById(R.id.andr2_lesson1_et_delete1element)

}

 fun setOnClick() {
listener = View.OnClickListener { view ->
    val db = dbHelper.writableDatabase
    when (view.id) {
        R.id.btn_a2_l1_open_fields -> openFields()
        R.id.andr2_lesson1_add_new -> addNewOrUpdate()
        R.id.btn_a2_l1_load -> loadFromDb(null)
        R.id.btn_a2_l1_delete -> {
            val clearCount = db.delete("mytable", null, null)
            persons.clear()
            Log.d(MY_TAG, "deleted rows count = $clearCount")
        }
        R.id.btn_a2l1_example -> createRandomFields()
        R.id.andr2_lesson1_btn_update -> {
            btnAddNew.text = "update"
            fieldsLayout.visibility = View.VISIBLE
            etIdSet.visibility = View.VISIBLE
        }
        R.id.andr2_lesson1_cancel -> {
            etIdSet.visibility = View.GONE
            fieldsLayout.visibility = View.GONE
        }
        R.id.andr2_lesson1_btn_delete1element -> {
            fieldsLayout.visibility = View.GONE
            etIdSet.visibility = View.GONE
            delete1ElementLayout.visibility = View.VISIBLE
        }
        R.id.andr2_lesson1_btn_delete1element_in_layout -> {
            if (etDelete1Element.text.toString().equals("", ignoreCase = true))
                break
            val idInEt = etDelete1Element.text.toString()
            val delCount = db.delete("mytable", "id = $idInEt", null)
            Toast.makeText(applicationContext, (delCount).toString() + " запись удалена id = " + idInEt, Toast.LENGTH_SHORT).show()
            delete1ElementLayout.visibility = View.GONE
            if (radioButtonsLayout.visibility == View.GONE)
                radioButtonsLayout.visibility = View.VISIBLE
            else
                radioButtonsLayout.visibility = View.GONE
        }
        R.id.andr2_lesson1_btn_sort -> if (radioButtonsLayout.visibility == View.GONE)
            radioButtonsLayout.visibility = View.VISIBLE
        else
            radioButtonsLayout.visibility = View.GONE
        R.id.android2_lesson1_btn_select -> when (radioGroup.checkedRadioButtonId) {
            R.id.android2_lesson1_radio_id -> loadFromDb("id")
            R.id.android2_lesson1_radio_name -> loadFromDb("fio")
            R.id.android2_lesson1_radio_age -> loadFromDb("age")
            R.id.android2_lesson1_radio_cost -> loadFromDb("cost")
            R.id.android2_lesson1_radio_post -> loadFromDb("post")
        }
    }
    dbHelper.close()
}

}

 fun openFields() {
     btnAddNew.text = "add+"
     etIdSet.visibility = View.GONE
if (fieldsLayout.visibility == View.GONE)
{
    fieldsLayout.visibility = View.VISIBLE
}
}

 fun loadFromDb(orderBy:String?) {
 //TODO: можно захуярить все это дело в асинк таск, с анимацией  загрузки
        persons.clear()
val id:Int
val fio:String
val age:Int
val post:String
val cost:Int
val db = dbHelper.writableDatabase
val c = db.query("mytable", null, null, null, null, null, orderBy)
if (c.moveToFirst())
{
val idColIndex = c.getColumnIndex("id")
val fioColIndex = c.getColumnIndex("fio")
val ageColIndex = c.getColumnIndex("age")
val postColIndex = c.getColumnIndex("post")
val costColIndex = c.getColumnIndex("cost")

do
{
 //впринципе меня пока и устроит вот так вот) дальше в поток запилить, если зажористо будет
                id = c.getInt(idColIndex)
fio = c.getString(fioColIndex)
age = Integer.parseInt(c.getString(ageColIndex))
post = c.getString(postColIndex)
cost = Integer.parseInt(c.getString(costColIndex))

val person = Person(id, fio, age, post, cost)
persons.add(person)
Log.d(MY_TAG,
        "ID = " + id +
        ", name = " + fio +
        ", age = " + age +
        ", post = " + post +
        ", cost = " + cost)

}
while (c.moveToNext())
fillScrollView(persons)
}
else
allPersonsLayout.removeAllViews()
Log.d(MY_TAG, "0 rows")
c.close()
dbHelper.close()
}

 fun addNewOrUpdate() {
val id:Int
val fio:String
val age:Int
val post:String
val cost:Int
val cv = ContentValues()
val db = dbHelper.writableDatabase
if ((etAge.text.toString().equals("", ignoreCase = true) ||
etCost.text.toString().equals("", ignoreCase = true) ||
etPost.text.toString().equals("", ignoreCase = true) ||
etFio.text.toString().equals("", ignoreCase = true)))
{
Toast.makeText(applicationContext, "не верно заполнены поля",
Toast.LENGTH_SHORT).show()
return
}
if (etIdSet.visibility == View.GONE)
{
fio = etFio.text.toString()
age = Integer.parseInt(etAge.text.toString())
post = etPost.text.toString()
cost = Integer.parseInt(etCost.text.toString())
cv.put("fio", fio)
cv.put("age", age)
cv.put("post", post)
cv.put("cost", cost)
val rowID = db.insert("mytable", null, cv)
Log.d(MY_TAG, "row inserted, ID = $rowID")
    fieldsLayout.visibility = View.GONE
dbHelper.close()
return
}
else if ((etIdSet.visibility == View.VISIBLE && etIdSet.text.toString()
.equals("", ignoreCase = true)))
{
Toast.makeText(applicationContext, "введите ID", Toast.LENGTH_SHORT).show()
dbHelper.close()
return
}
else if (etIdSet.visibility == View.VISIBLE)
{
val idToUpdate = etIdSet.text.toString()
fio = etFio.text.toString()
age = Integer.parseInt(etAge.text.toString())
post = etPost.text.toString()
cost = Integer.parseInt(etCost.text.toString())
cv.put("fio", fio)
cv.put("age", age)
cv.put("post", post)
cv.put("cost", cost)
val idCount = db.update("mytable", cv, "id = ?", arrayOf(idToUpdate))
Toast.makeText(applicationContext, (idCount).toString() + " запись изменена id = " + idToUpdate, Toast.LENGTH_SHORT).show()
    fieldsLayout.visibility = View.GONE
    etIdSet.visibility = View.GONE
dbHelper.close()
return
}
}

 fun createRandomFields() {
val rand = Random()
val age = "" + (rand.nextInt(43) + 18)
val cost = "" + (((rand.nextInt(24) + 1) * 1000) + 20000)
val post = posts[rand.nextInt(posts.size)]
val fio = names[rand.nextInt(names.size)] + " " + i_o[rand.nextInt(i_o.size)]
etAge.setText(age)
etFio.setText(fio)
etCost.setText(cost)
etPost.setText(post)

}

 fun fillScrollView(persons:ArrayList<Person>) {
allPersonsLayout.removeAllViews()

for (p in persons)
{
val viewItem = layoutInflater.inflate(R.layout.android2_lesson1_item, null)
val id = viewItem.findViewById<TextView>(R.id.a2l1_item_id)
val age = viewItem.findViewById<TextView>(R.id.a2l1_item_age)
val fio = viewItem.findViewById<TextView>(R.id.a2l1_item_fio)
val cost = viewItem.findViewById<TextView>(R.id.a2l1_item_cost)
val post = viewItem.findViewById<TextView>(R.id.a2l1_item_post)
    id.text = "" + p.id
    fio.text = p.fio
    age.text = "" + p.age
    cost.text = "" + p.cost + "руб"
    post.text = p.post
 //TODO: на следующем уроке зафигачить сюда контекстное меню, для получения id и удаления из бд, с последующим обновлением коллекции
            allPersonsLayout.addView(viewItem)
}

}

private inner class Person private constructor(id:Int, fio:String, age:Int, post:String, cost:Int) {
 var id:Int = 0
internal set
 var fio:String
internal set
 var age:Int = 0
internal set
 var post:String
internal set
 var cost:Int = 0
internal set

init{
this.id = id
this.age = age
this.fio = fio
this.post = post
this.cost = cost
}
}

companion object {


 // Я просто оставлю это здесь, это урок 1 но на ява, а тот на котлин... пусть пока будут оба короче
     val DB_NAME = "MyDataBaseLesson1"
 val MY_TAG = "MyLogs"
}

}

 /* пометка на будущее:
----------------------------------------------------------------------------------------------------
query(table,columns,selection,selectionArgs,groupBy,having,orderBy)
table- имя таблицы
columns - список полей, которые хотим получить
selection - строка условия WHERE
selectionArgs - массив аргументов для selection, в selection можно использовать знаки ?, которые
    будут заменены этими значениями
groupBy - группировка
having - использование условий для агрегатных функций
orderBy - сортировка
----------------------------------------------------------------------------------------------------
TODO: уроки 37-38 в STARTANDROID пока пропускаю, вернутся чтобы изучить запросы из связных таблиц и транзакции. изучил только устно
 */