package com.example.battleground

import android.R
import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.ArrayAdapter
import android.widget.NumberPicker
import androidx.appcompat.app.AppCompatActivity
import com.davemorrissey.labs.subscaleview.ImageSource
import com.example.battleground.databinding.AddBattleLogActivityBinding
import com.example.battleground.ui.main.SelectHeroFragment
import android.widget.NumberPicker.OnValueChangeListener





class AddBattleLogActivity: AppCompatActivity() {
    private lateinit var binding: AddBattleLogActivityBinding
    private var SELECT_PICTURE = 200

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = AddBattleLogActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val rankMin = 1
        val rankMax = 8
        binding.rank.minValue = rankMin
        binding.rank.maxValue = rankMax

        val min = -200
        val max = 200
        binding.rateChange.minValue = 0
        binding.rateChange.maxValue = max - min
        binding.rateChange.value = 0 + max
        binding.rateChange.setFormatter {
                index ->
            (index + min).toString()
        }

        binding.goToZero.setOnClickListener {
            binding.rateChange.value = 0 + max
        }

        binding.goToMinusHundred.setOnClickListener {
            binding.rateChange.value = 0 + max -100
        }

        binding.goToPlusHundred.setOnClickListener {
            binding.rateChange.value = 0 + 100
        }

        binding.rateChange.setOnValueChangedListener { picker, oldVal, newVal ->
            //入力値が変更されるたびに入れたい処理
            binding.rateChange.value = newVal
        }

        // 保存ボタン押下時処理
        binding.saveButton.setOnClickListener {
            val newBattleLogData = Intent()
            newBattleLogData.putExtra("rateText", binding.rateEditText.text)
            newBattleLogData.putExtra("memoText", binding.memoEditText.text)
            newBattleLogData.putExtra("rateChange",binding.rateChange.value + min)
            newBattleLogData.putExtra("rank",binding.rank.value)

            Log.i("AddBattleLogActivity", "rate: " + binding.rateEditText.text
                    + "memo: " + binding.memoEditText.text + "rateChange: "
                    + binding.rateChange.value + min)
            finish()
        }

        // スクリーンショット選択
        binding.screenshot.setOnClickListener {
            imageChooser()
//            val imageViewEnlarged = SubsamplingScaleImageView(applicationContext)
//            val bitmap = (binding.screenshot as BitmapDrawable).bitmap
//            imageViewEnlarged.setImage(ImageSource.bitmap(bitmap))
//            val builder: AlertDialog.Builder = Builder(this)
//            builder.setView(imageViewEnlarged)
//            builder.setNegativeButton(R.string.label_close, null)
//            builder.show()
        }

        // 背景タッチでEditTextのキーボードを閉じる
        binding.linearLayout.setOnClickListener {
            hideSoftKeyboard(this)
        }

        binding.selectHero.setOnClickListener {
//            transitionTo(SelectHeroFragment.newInstance())
//            supportFragmentManager.beginTransaction().replace(R.id.container, SelectHeroFragment.newInstance())
//                .commitNow()
        }
        val deckTypeArray: ArrayList<String> = ArrayList()
        for (i in DeckTypeData.values().indices) {
            val deckType = DeckTypeData.values()[i].DeckType
            deckTypeArray.add(deckType)
        }
        val adapter: ArrayAdapter<String> = ArrayAdapter(this, R.layout.simple_list_item_1, deckTypeArray)
        binding.gridView.adapter = adapter
    }

    /**
     * EditTextのキーボードを閉じる処理
     */
    private fun hideSoftKeyboard(activity: Activity) {
        val inputMethodManager = activity.getSystemService(
            INPUT_METHOD_SERVICE
        ) as InputMethodManager
        if (inputMethodManager.isAcceptingText) {
            inputMethodManager.hideSoftInputFromWindow(
                activity.currentFocus!!.windowToken,
                0
            )
        }
    }


    /**
     * 端末から選択した写真のデータを受け取る
     */
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode === RESULT_OK) {

            // compare the resultCode with the
            // SELECT_PICTURE constant
            if (requestCode === SELECT_PICTURE) {
                // Get the url of the image from data
                val selectedImageUri: Uri? = data?.data
                if (null != selectedImageUri) {
                    // update the preview image in the layout
                    binding.screenshot.setImage(ImageSource.uri(selectedImageUri))
                    binding.selectPictureText.visibility = View.INVISIBLE
                }
            }
        }
    }

    /**
     * 端末から写真を選択
     */
    private fun imageChooser() {

        // create an instance of the
        // intent of the type image
        val i = Intent()
        i.type = "image/*"
        i.action = Intent.ACTION_GET_CONTENT

        // pass the constant to compare it
        // with the returned requestCode
        startActivityForResult(Intent.createChooser(i, "Select Picture"), SELECT_PICTURE)
    }

    private fun transitionTo(newFragment: BaseFragment): Int {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
//        fragmentTransaction.replace(R.id.container, newFragment, newFragment::class.simpleName)
        fragmentTransaction.addToBackStack(newFragment::class.simpleName)
        return fragmentTransaction.commit()
    }


}