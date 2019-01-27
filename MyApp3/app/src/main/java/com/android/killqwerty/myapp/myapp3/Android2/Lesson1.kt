package com.android.killqwerty.myapp.myapp3.Android2

import android.content.ContentValues
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.widget.*

import com.android.killqwerty.myapp.myapp3.R
import kotlinx.android.synthetic.main.android2_lesson1.*

import java.util.Random

class Lesson1 : AppCompatActivity() {
    val DB_NAME = "MyDataBaseLesson1"
    val MY_TAG = "MyLogs"

    //TODO: Захерачить загрузку бд и заполнения колекции в асинктаск, а то чет жирно
    //TODO: запилить заполнения массив , внутри метода с проверкой, и будет счастье
    val names = arrayOf("Иванов", "Петров", "Сидоров", "Антонов", "Песков", "Никульшин", "Ивлеев", "Захаров", "Марченко", "Путин", "Медведев", "Навальный", "Дробинин", "Винярский", "Ильченко", "Крюков")
    val i_o = arrayOf("А.А", "А.В", "Е.Г", "О.Ю", "Г.О", "П.П", "П.В", "Н.О", "Е.А", "О.Г", "В.Е", "А.Н", "А.Б", "Б.Ю")
    val posts = arrayOf("Напальник отдела", "уборщик", "бармен", "тестировщик", "царь во дворца", "помощник директора", "системный администратор", "официант", "халдей", "повар", "шеф-повар", "администратор", "бухгалтер", "закупщик", "грузчик", "программист", "доставщик", "поставщик", "кладовщик", "старший бухгалтер", "SEO", "фотограф", "водитель", "стажер", "директор")
    internal var myListener: View.OnClickListener? = null
//    var btnOpenFields : Button? = null                   // думал думал короче, ничего умнее пока не придумал и примеров не нашел...
//    var btnLoad : Button? = null                         // доступ чисто по id мне пока не очень то тут нужен
//    var btnDelete : Button? = null
//    var btnExample : Button? = null
//    var btnAddNew: Button? = null
//    var btnUpdate : Button? = null
//    var btnCancel : Button? = null
//    var btnDelete1Element : Button? = null
//    var btnDelete1ElementInLayout : Button? = null
//    var btnSort : Button? = null
//    var btnSelect : Button? = null

    var fieldsLayout : LinearLayout? = null
    var allPersonsLayout : LinearLayout? = null
    var delete1ElementLayout : LinearLayout? = null
    var radioButtonsLayout : LinearLayout? = null
    var radioGroup : RadioGroup? = null

    var etFio : EditText? = null
    var etAge : EditText? = null
    var etPost : EditText? = null
    var etCost : EditText? = null
    var etIdSet : EditText? = null
    var etDelete1Element : EditText? = null
    var dbHelper = Lesson1DbHelper(this, DB_NAME)

//    btn_a2_l1_open_fields.setOnClickListener { listener }
//    btn_a2_l1_delete.setOnClickListener { listener }
//    btn_a2_l1_load.setOnClickListener { listener }
//    btn_a2l1_example.setOnClickListener { listener }
//    andr2_lesson1_add_new.setOnClickListener { listener }
//    andr2_lesson1_btn_update.setOnClickListener { listener }
//    andr2_lesson1_cancel.setOnClickListener { listener }
//    andr2_lesson1_btn_delete1element.setOnClickListener { listener }
//    andr2_lesson1_btn_delete1element_in_layout.setOnClickListener { listener }
//    andr2_lesson1_btn_sort.setOnClickListener { listener }
//    android2_lesson1_btn_select.setOnClickListener { listener }
//    andr2_lesson1_radiogroup1.setOnClickListener { listener }


//    internal var radioGroup: RadioGroup
//    internal var etFio: EditText
//    internal var etAge: EditText
//    internal var etPost: EditText
//    internal var etCost: EditText
//      var etIdSet: EditText? = null // надо чет придумать нулэйбл хуйня это все
//    internal var etDelete1Element: EditText
 //     val dbHelper = Lesson1DbHelper(this, DB_NAME)
//    internal var allPersonsLayout: LinearLayout
//    internal var fieldsLayout: LinearLayout
//    internal var delete1ElementLayout: LinearLayout
//    internal var radioButtonsLayout: LinearLayout
      private var persons: MutableList<Person>? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.android2_lesson1)
        setButtonsAndView()
        //val persons = ArrayList<Any>()
        fieldsLayout!!.visibility = View.GONE
        etIdSet!!.visibility = View.GONE
        delete1ElementLayout!!.visibility = View.GONE
        radioButtonsLayout!!.visibility = View.GONE

    }

    fun setButtonsAndView() {
        setOnClick()
        //TODO: запилить второй листенер, без использования базы данных
//        btnOpenFields = btn_a2_l1_open_fields        // последняя нерабочая версия
//        btnLoad = btn_a2_l1_load
//        btnDelete = btn_a2_l1_delete
//        btnExample = btn_a2l1_example
//        //btnAddNew = andr2_lesson1_add_new
//        btnUpdate = andr2_lesson1_btn_update
//        btnCancel = andr2_lesson1_cancel
//        btnDelete1Element = andr2_lesson1_btn_delete1element
//        btnDelete1ElementInLayout = andr2_lesson1_btn_delete1element_in_layout
//        btnSort = andr2_lesson1_btn_sort
//        btnSelect = android2_lesson1_btn_select
//
//        btnOpenFields?.setOnClickListener { myListener }
//        btnDelete?.setOnClickListener { myListener }
//        btnLoad?.setOnClickListener { myListener }
//        btnExample?.setOnClickListener { myListener }
//        btnAddNew?.setOnClickListener { myListener }
//
//        btnUpdate?.setOnClickListener { myListener }
//        btnCancel?.setOnClickListener { myListener }
//        btnDelete1Element?.setOnClickListener { myListener }
//        btnDelete1ElementInLayout?.setOnClickListener {myListener }
//        btnSort?.setOnClickListener { myListener }
//        btnSelect?.setOnClickListener { myListener }


    btn_a2_l1_open_fields.setOnClickListener { myListener }      //а это если не будет работать то я афк нахер
    btn_a2_l1_delete.setOnClickListener { myListener }
    btn_a2_l1_load.setOnClickListener { myListener }
    btn_a2l1_example.setOnClickListener { myListener }
    andr2_lesson1_add_new.setOnClickListener { myListener }
    andr2_lesson1_btn_update.setOnClickListener { myListener }
    andr2_lesson1_cancel.setOnClickListener { myListener }
    andr2_lesson1_btn_delete1element.setOnClickListener { myListener }
    andr2_lesson1_btn_delete1element_in_layout.setOnClickListener { myListener }
    andr2_lesson1_btn_sort.setOnClickListener { myListener }
    android2_lesson1_btn_select.setOnClickListener { myListener }
    andr2_lesson1_radiogroup1.setOnClickListener { myListener }

        fieldsLayout = andr2_lesson1_fields_linear
        allPersonsLayout = andr2_lesson1_linearl_in_scroll
        delete1ElementLayout = andr2_lesson1_linear_delete_element
        radioButtonsLayout = andr2_lesson1_layout_with_radiogroup
        radioGroup = andr2_lesson1_radiogroup1

        etFio = a2l1_et_f_i_o
        etAge = a2l1_et_age
        etPost = a2l1_et_post
        etCost = a2l1_et_cost
        etIdSet = a2l1_et_set_id
        etDelete1Element = andr2_lesson1_et_delete1element
//        andr2_lesson1_radiogroup1.setOnClickListener { listener }

//        btnOpenFields = findViewById(R.id.btn_a2_l1_open_fields)
//        btnDelete = findViewById(R.id.btn_a2_l1_delete)
//        btnLoad = findViewById(R.id.btn_a2_l1_load)
//        btnExample = findViewById(R.id.btn_a2l1_example)
//        btnAddNew = findViewById(R.id.andr2_lesson1_add_new)
//        btnUpdate = findViewById(R.id.andr2_lesson1_btn_update)
//        btnCancel = findViewById(R.id.andr2_lesson1_cancel)
//        btnDelete1Element = findViewById(R.id.andr2_lesson1_btn_delete1element)
//        BtnDelete1ElementInLayout = findViewById(R.id.andr2_lesson1_btn_delete1element_in_layout)
//        btnSort = findViewById(R.id.andr2_lesson1_btn_sort)
//        btnSelect = findViewById(R.id.android2_lesson1_btn_select)
//        //val radioGroup = findViewById<RadioGroup>(R.id.andr2_lesson1_radiogroup1)

//        btnOpenFields.setOnClickListener(listener)
//        btnDelete.setOnClickListener(listener)
//        btnLoad.setOnClickListener(listener)
//        btnExample.setOnClickListener(listener)
//        btnAddNew.setOnClickListener(listener)
//        btnUpdate.setOnClickListener(listener)
//        btnCancel.setOnClickListener(listener)
//        btnDelete1Element.setOnClickListener(listener)
//        BtnDelete1ElementInLayout.setOnClickListener(listener)
//        btnSort.setOnClickListener(listener)
//        btnSelect.setOnClickListener(listener)

//        etFio = findViewById(R.id.a2l1_et_f_i_o)
//        etAge = findViewById(R.id.a2l1_et_age)
//        etPost = findViewById(R.id.a2l1_et_post)
//        etCost = findViewById(R.id.a2l1_et_cost)                                                   это просто закоментил без обьявления
//        etIdSet = findViewById(R.id.a2l1_et_set_id)
//        etDelete1Element = findViewById(R.id.andr2_lesson1_et_delete1element)

    }

    fun setOnClick() {
        myListener = View.OnClickListener { view: View? ->
            val db = dbHelper.writableDatabase
            Toast.makeText(applicationContext,"click",Toast.LENGTH_LONG).show()
            when (view?.id) {
                btn_a2_l1_open_fields.id -> openFields()
                R.id.btn_a2_l1_open_fields -> openFields()
                R.id.andr2_lesson1_add_new -> addNewOrUpdate()
                R.id.btn_a2_l1_load -> loadFromDb(null)
                R.id.btn_a2_l1_delete -> {
                    val clearCount = db.delete("mytable", null, null)
                    persons?.clear()
                    Log.d(MY_TAG, "deleted rows count = $clearCount")
                }
                R.id.btn_a2l1_example -> createRandomFields()
                R.id.andr2_lesson1_btn_update -> {
                   // btnAddNew?.text = "update"                   убрал лишь бы проверить
                    fieldsLayout?.visibility = View.VISIBLE
                    etIdSet?.visibility = View.VISIBLE
                }
                R.id.andr2_lesson1_cancel -> {
                    etIdSet?.visibility = View.GONE
                    fieldsLayout?.visibility = View.GONE
                }
                R.id.andr2_lesson1_btn_delete1element -> {
                    fieldsLayout?.visibility = View.GONE
                    etIdSet?.visibility = View.GONE
                    delete1ElementLayout?.visibility = View.VISIBLE
                }
                R.id.andr2_lesson1_btn_delete1element_in_layout -> {
                    if (etDelete1Element?.text.toString().equals("", ignoreCase = true)) {
                        return@OnClickListener // ну тут явно что то не так нахер
                    }
                    val idInEt = etDelete1Element?.text.toString()
                    val delCount = db.delete("mytable", "id = $idInEt", null)
                    Toast.makeText(applicationContext, delCount.toString() + " запись удалена id = " + idInEt, Toast.LENGTH_SHORT).show()
                    delete1ElementLayout?.visibility = View.GONE
                    if (radioButtonsLayout?.visibility == View.GONE) {
                        radioButtonsLayout?.visibility = View.VISIBLE
                    }
                    else {
                        radioButtonsLayout?.visibility = View.GONE
                    }
                }
                R.id.andr2_lesson1_btn_sort -> if (radioButtonsLayout?.visibility == View.GONE)
                    radioButtonsLayout?.visibility = View.VISIBLE
                else
                    radioButtonsLayout?.visibility = View.GONE
                R.id.android2_lesson1_btn_select -> when (radioGroup?.checkedRadioButtonId) {
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
       // btnAddNew?.text = "add+"                              убрал для проверки
        etIdSet?.visibility = View.GONE
        if (fieldsLayout?.visibility == View.GONE) {
            fieldsLayout?.visibility = View.VISIBLE
        }
    }

    fun loadFromDb(orderBy: String?) {
        //TODO: можно захуярить все это дело в асинк таск, с анимацией  загрузки
        persons?.clear()
        var id: Int
        var fio: String
        var age: Int
        var post: String
        var cost: Int
        val db = dbHelper.writableDatabase
        val c = db.query("mytable", null, null, null, null, null, orderBy)
        if (c.moveToFirst()) {
            val idColIndex = c.getColumnIndex("id")
            val fioColIndex = c.getColumnIndex("fio")
            val ageColIndex = c.getColumnIndex("age")
            val postColIndex = c.getColumnIndex("post")
            val costColIndex = c.getColumnIndex("cost")

            do {
                //впринципе меня пока и устроит вот так вот) дальше в поток запилить, если зажористо будет
                id = c.getInt(idColIndex)
                fio = c.getString(fioColIndex)
                age = Integer.parseInt(c.getString(ageColIndex))
                post = c.getString(postColIndex)
                cost = Integer.parseInt(c.getString(costColIndex))

                val person = Person(id, fio, age, post, cost)
                persons?.add(person)
                Log.d(MY_TAG,
                        "ID = " + id +
                                ", name = " + fio +
                                ", age = " + age +
                                ", post = " + post +
                                ", cost = " + cost)

            } while (c.moveToNext())
            fillScrollView(persons)
        } else
            allPersonsLayout?.removeAllViews()
        Log.d(MY_TAG, "0 rows")
        c.close()
        dbHelper.close()
    }

    fun addNewOrUpdate() {
        //val id: Int
        val fio: String
        val age: Int
        val post: String
        val cost: Int
        val cv = ContentValues()
        val db = dbHelper.writableDatabase
        if (etAge?.text.toString().equals("", ignoreCase = true) ||
                etCost?.text.toString().equals("", ignoreCase = true) ||
                etPost?.text.toString().equals("", ignoreCase = true) ||
                etFio?.text.toString().equals("", ignoreCase = true)) {
            Toast.makeText(applicationContext, "не верно заполнены поля",
                    Toast.LENGTH_SHORT).show()
            return
        }
        if (etIdSet?.visibility == View.GONE) {
            fio = etFio?.text.toString()
            age = Integer.parseInt(etAge?.text.toString())
            post = etPost?.text.toString()
            cost = Integer.parseInt(etCost?.text.toString())
            cv.put("fio", fio)
            cv.put("age", age)
            cv.put("post", post)
            cv.put("cost", cost)
            val rowID = db.insert("mytable", null, cv)
            Log.d(MY_TAG, "row inserted, ID = $rowID")
            fieldsLayout?.visibility = View.GONE
            dbHelper.close()
            return
        } else if (etIdSet?.visibility == View.VISIBLE && etIdSet?.text.toString()
                        .equals("", ignoreCase = true)) {
            Toast.makeText(applicationContext, "введите ID", Toast.LENGTH_SHORT).show()
            dbHelper.close()
            return
        } else if (etIdSet?.visibility == View.VISIBLE) {
            val idToUpdate = etIdSet?.text.toString()
            fio = etFio?.text.toString()
            age = Integer.parseInt(etAge?.text.toString())
            post = etPost?.text.toString()
            cost = Integer.parseInt(etCost?.text.toString())
            cv.put("fio", fio)
            cv.put("age", age)
            cv.put("post", post)
            cv.put("cost", cost)
            val idCount = db.update("mytable", cv, "id = ?", arrayOf(idToUpdate))
            Toast.makeText(applicationContext, idCount.toString() + " запись изменена id = " + idToUpdate, Toast.LENGTH_SHORT).show()
            fieldsLayout?.visibility = View.GONE
            etIdSet?.visibility = View.GONE
            dbHelper.close()
            return
        }
    }

    fun createRandomFields() {
        val rand = Random()
        val age = "" + (rand.nextInt(43) + 18)
        val cost = "" + ((rand.nextInt(24) + 1) * 1000 + 20000)
        val post = posts[rand.nextInt(posts.size)]
        val fio = names[rand.nextInt(names.size)] + " " + i_o[rand.nextInt(i_o.size)]
        etAge?.setText(age)
        etFio?.setText(fio)
        etCost?.setText(cost)
        etPost?.setText(post)

    }

    fun fillScrollView(persons: MutableList<Person>?) {
        allPersonsLayout?.removeAllViews()
        if (persons != null) {
            for (p in persons) {
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
                allPersonsLayout?.addView(viewItem)
            }
        }

    }
    data class Person (val id: Int, val fio: String, val age: Int, val post: String, val cost: Int)
//     inner class Person private constructor(id: Int, fio: String, age: Int, post: String, cost: Int) {
//        var id: Int = 0
//            internal set
//        var fio: String
//            internal set
//        var age: Int = 0
//            internal set
//        var post: String
//            internal set
//        var cost: Int = 0
//            internal set
//
//         init {
//            this.id = id
//            this.age = age
//            this.fio = fio
//            this.post = post
//            this.cost = cost
//        }
//    }





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