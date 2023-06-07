package com.example.dietpro

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.dietpro.viewmodel.UserCRUDViewModel
import java.util.*

class AddUsereatsMealFragment : Fragment() , View.OnClickListener, AdapterView.OnItemSelectedListener {
    private lateinit var root: View
    private lateinit var myContext: Context
    private lateinit var model: MealCRUDViewModel
    private lateinit var modelUser: UserCRUDViewModel
    
    private lateinit var mealBean: MealBean

    private lateinit var mealIdSpinner: Spinner
    private lateinit var userNameSpinner: Spinner

    private var allMealmealId: List<String> = ArrayList()
    private var allUseruserName: List<String> = ArrayList()

    private lateinit var mealIdTextField: EditText
    private var mealIdData = ""
    private lateinit var userNameTextField: EditText
    private var userNameData = ""
    
    private lateinit var okButton: Button
    private lateinit var cancelButton: Button

    companion object {
        fun newInstance(c: Context): AddUsereatsMealFragment {
            val fragment = AddUsereatsMealFragment()
            val args = Bundle()
            fragment.arguments = args
            fragment.myContext = c
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        root = inflater.inflate(R.layout.addusereatsmeal_layout, container, false)
        super.onViewCreated(root, savedInstanceState)
        return root
    }

    override fun onResume() {
        super.onResume()
        model = MealCRUDViewModel.getInstance(myContext)
        modelUser = UserCRUDViewModel.getInstance(myContext)
        
	    mealIdTextField = root.findViewById<View>(R.id.addUsereatsMealmealIdField) as EditText
	        mealIdSpinner = root.findViewById<View>(R.id.addUsereatsMealmealIdSpinner) as Spinner

	     	model.allMealMealIds.observe(viewLifecycleOwner, androidx.lifecycle.Observer { allMealmealIds ->
			allMealmealIds.let {
			allMealmealId = allMealmealIds
			val mealIdAdapter = ArrayAdapter(myContext, android.R.layout.simple_spinner_item, allMealmealId)
            mealIdAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            mealIdSpinner.adapter = mealIdAdapter
            mealIdSpinner.onItemSelectedListener = this
				}
			})
        userNameTextField = root.findViewById<View>(R.id.addUsereatsMealuserNameField) as EditText
	        userNameSpinner = root.findViewById<View>(R.id.addUsereatsMealuserNameSpinner) as Spinner

        allUseruserName = modelUser.allUserUserNames()
        userNameTextField = root.findViewById<View>(R.id.addUsereatsMealuserNameField) as EditText
        userNameSpinner = root.findViewById<View>(R.id.addUsereatsMealuserNameSpinner) as Spinner
        val userNameAdapter: ArrayAdapter<String> =
            ArrayAdapter<String>(myContext, android.R.layout.simple_spinner_item, allUseruserName)
        userNameAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        userNameSpinner.setAdapter(userNameAdapter)
        userNameSpinner.setOnItemSelectedListener(this)

        mealBean = MealBean(myContext)

        okButton = root.findViewById(R.id.addUsereatsMealOK)
        okButton.setOnClickListener(this)
        cancelButton = root.findViewById(R.id.addUsereatsMealCancel)
        cancelButton.setOnClickListener(this)
    }

    override fun onItemSelected(parent: AdapterView<*>, v: View?, position: Int, id: Long) {
        if (parent === mealIdSpinner) {
            mealIdTextField.setText(allMealmealId[position])
        }
        if (parent ==userNameSpinner) {
            userNameTextField.setText(allUseruserName[position])
        }
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
    	//onNothingSelected
    }

    override fun onClick(v: View?) {
        val imm = myContext.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        try {
            imm.hideSoftInputFromWindow(v?.windowToken, 0)
        } catch (e: Exception) {
        	 e.message
        }

        when (v?.id) {
            R.id.addUsereatsMealOK -> {
                addUsereatsMealOK()
            }
            R.id.addUsereatsMealCancel -> {
                addUsereatsMealCancel()
            }
        }
    }

    private fun addUsereatsMealOK() {
        mealIdData = mealIdTextField.getText().toString() + ""
        mealBean.setMealId(mealIdData)
        userNameData = userNameTextField.getText().toString() + ""
        mealBean.setUserName(userNameData)
        if (mealBean.isAddUsereatsMealError()) {
            Log.w(javaClass.name, mealBean.errors())
            Toast.makeText(myContext, "Errors: " + mealBean.errors(), Toast.LENGTH_LONG).show()
        } else {
        	viewLifecycleOwner.lifecycleScope.launchWhenStarted {
                mealBean.addUsereatsMeal()
	            Toast.makeText(myContext, "added!", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun addUsereatsMealCancel() {
        mealBean.resetData()
        mealIdTextField.setText("")
        userNameTextField.setText("")
    }
}
