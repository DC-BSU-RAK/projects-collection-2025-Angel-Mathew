package com.example.cocomix

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.PopupWindow
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private lateinit var coconutButton: ImageView
    private lateinit var caramelButton: ImageView
    private lateinit var waferButton: ImageView
    private lateinit var hazelnutButton: ImageView
    private lateinit var nougatButton: ImageView
    private lateinit var peanutButton: ImageView
    private lateinit var mixButton: ImageButton
    private lateinit var outputImage: ImageView
    private val selectedIngredients = mutableSetOf<String>()
    private val maxIngredients = 3

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.kitchen_cocomix_mainactivity)

        initializeViews()
        setupIngredientClickListeners()
        setupMixButtonListener()

        // Instruction popup
        val instrbtn: ImageButton = findViewById(R.id.instrubtn)
        instrbtn.setOnClickListener {
            val inflater = getSystemService(LAYOUT_INFLATER_SERVICE) as LayoutInflater
            val popupView = inflater.inflate(R.layout.activity_instruction_popupscreen, null)
            val instrscreen = PopupWindow(popupView, 950, 970, true)
            instrscreen.showAtLocation(popupView, Gravity.CENTER, 0, 0)

            val closebutton: Button = popupView.findViewById(R.id.closebutton)
            closebutton.setOnClickListener {
                instrscreen.dismiss()
            }
        }
    }

    private fun initializeViews() {
        try {
            coconutButton = findViewById(R.id.coconutbutton)
            caramelButton = findViewById(R.id.caramelbutton)
            waferButton = findViewById(R.id.waferbutton)
            hazelnutButton = findViewById(R.id.hazelnutbutton)
            nougatButton = findViewById(R.id.nougatbutton)
            peanutButton = findViewById(R.id.peanutbutton)
            mixButton = findViewById(R.id.Mbutton)
            outputImage = findViewById(R.id.outputImage)


            outputImage.visibility = View.GONE

            Log.d("ChocolateMixer", "All views initialized successfully")
        } catch (e: Exception) {
            Log.e("ChocolateMixer", "Error initializing views: ${e.message}")
        }
    }

    private fun setupIngredientClickListeners() {

        coconutButton.setOnClickListener { onIngredientClick("coconut", coconutButton) }
        caramelButton.setOnClickListener { onIngredientClick("caramel", caramelButton) }
        waferButton.setOnClickListener { onIngredientClick("wafer", waferButton) }
        hazelnutButton.setOnClickListener { onIngredientClick("hazelnut", hazelnutButton) }
        nougatButton.setOnClickListener { onIngredientClick("nougat", nougatButton) }
        peanutButton.setOnClickListener { onIngredientClick("peanut", peanutButton) }
    }

    private fun setupMixButtonListener() {
        mixButton.setOnClickListener { view ->
            mixbtn(view)
        }
    }

    private fun onIngredientClick(ingredient: String, imageView: ImageView) {
        Log.d("ChocolateMixer", "Ingredient clicked: $ingredient")

        if (selectedIngredients.contains(ingredient)) {
            // Deselect ingredient
            selectedIngredients.remove(ingredient)
            imageView.alpha = 1.0f
            imageView.scaleX = 1.0f
            imageView.scaleY = 1.0f
            Log.d("ChocolateMixer", "Deselected: $ingredient")
        } else {
            // Checks if max ingredients reached
            if (selectedIngredients.size >= maxIngredients) {
                Toast.makeText(this, "You can select maximum $maxIngredients ingredients!", Toast.LENGTH_SHORT).show()
                return
            }

            // Select ingredient
            selectedIngredients.add(ingredient)
            imageView.alpha = 0.2f
            imageView.scaleX = 1.1f
            imageView.scaleY = 1.1f
            Log.d("ChocolateMixer", "Selected: $ingredient")
        }

        updateSelectionFeedback()
        Log.d("ChocolateMixer", "Current selection: $selectedIngredients")
    }

    private fun updateSelectionFeedback() {
        val selectedCount = selectedIngredients.size
        when {
            selectedCount == 0 -> {
                Toast.makeText(this, "Select at least 1 ingredient", Toast.LENGTH_SHORT).show()
            }
            selectedCount == maxIngredients -> {
                Toast.makeText(this, "Maximum ingredients selected! Ready to mix.", Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun mixbtn(view: View) {
        Log.d("ChocolateMixer", "Mix button clicked")
        Log.d("ChocolateMixer", "Selected ingredients: $selectedIngredients")

        if (selectedIngredients.isEmpty()) {
            Toast.makeText(this, "Please select at least one ingredient before mixing!", Toast.LENGTH_LONG).show()
            return
        }

        // start mixing process
        showLoadingScreen()

        // mixing process
        Handler(Looper.getMainLooper()).postDelayed({
            hideLoadingScreen()
            showFinalChocolate()
        }, 3000) // 3 seconds loading time
    }

    private fun showLoadingScreen() {
        Log.d("ChocolateMixer", "Showing loading screen")

        try {
            // Hide  ingredient buttons
            hideIngredientButtons()

            // Hide mix button while  loading
            mixButton.visibility = View.GONE

            // Show loading image (mixing bowl)
            outputImage.visibility = View.VISIBLE

            // Check if mixing_bowl exist
            try {
                outputImage.setImageResource(R.drawable.mixing_bowl)
                Log.d("ChocolateMixer", "Set mixing bowl image")
            } catch (e: Exception) {
                Log.w("ChocolateMixer", "mixing_bowl not found, using fallback")
                outputImage.setImageResource(R.drawable.chocolate_topping)
            }

            Toast.makeText(this, "Mixing your chocolate...", Toast.LENGTH_SHORT).show()
        } catch (e: Exception) {
            Log.e("ChocolateMixer", "Error in showLoadingScreen: ${e.message}")
        }
    }

    private fun hideLoadingScreen() {
        Log.d("ChocolateMixer", "Hiding loading screen")

        try {
            // Show ingredient button
            showIngredientButtons()

            // Show mix button
            mixButton.visibility = View.VISIBLE
        } catch (e: Exception) {
            Log.e("ChocolateMixer", "Error in hideLoadingScreen: ${e.message}")
        }
    }

    private fun hideIngredientButtons() {
        coconutButton.visibility = View.INVISIBLE
        caramelButton.visibility = View.INVISIBLE
        waferButton.visibility = View.INVISIBLE
        hazelnutButton.visibility = View.INVISIBLE
        nougatButton.visibility = View.INVISIBLE
        peanutButton.visibility = View.INVISIBLE
    }

    private fun showIngredientButtons() {
        coconutButton.visibility = View.VISIBLE
        caramelButton.visibility = View.VISIBLE
        waferButton.visibility = View.VISIBLE
        hazelnutButton.visibility = View.VISIBLE
        nougatButton.visibility = View.VISIBLE
        peanutButton.visibility = View.VISIBLE
    }

    private fun showFinalChocolate() {
        Log.d("ChocolateMixer", "Showing final chocolate")

        try {
            val chocolateImage = getChocolateImage()
            Log.d("ChocolateMixer", "Using drawable resource ID: $chocolateImage")

            // Show the output image
            outputImage.visibility = View.VISIBLE
            outputImage.setImageResource(chocolateImage)

            // Show success message
            val ingredientList = selectedIngredients.joinToString(", ")
            Toast.makeText(this, "Your chocolate with $ingredientList is ready!", Toast.LENGTH_LONG).show()

            // Reset selection after showing result
            Handler(Looper.getMainLooper()).postDelayed({
                resetSelection()
            }, 5000) // Show result for 5 seconds

        } catch (e: Exception) {
            Log.e("ChocolateMixer", "Error showing final chocolate: ${e.message}")
            Toast.makeText(this, "Error displaying chocolate!", Toast.LENGTH_SHORT).show()
            resetSelection()
        }
    }

    private fun getChocolateImage(): Int {
        Log.d("ChocolateMixer", "Getting chocolate image for: $selectedIngredients")

        return try {
            when {
                selectedIngredients.size == 1 -> {
                    when (selectedIngredients.first()) {
                        "coconut" -> R.drawable.coconut_chocolate
                        "caramel" -> R.drawable.caramelchocolate
                        "wafer" -> R.drawable.wafer_chocolate
                        "hazelnut" -> R.drawable.hazelnut_chocolate
                        "nougat" -> R.drawable.nougatchocolate
                        "peanut" -> R.drawable.peanutchocolate
                        else -> R.drawable.sorry__no_chocolate_found
                    }
                }
                selectedIngredients.contains("coconut") && selectedIngredients.contains("peanut") -> {
                    R.drawable.sorry__no_chocolate_found
                }
                selectedIngredients.size >= 2 -> {
                    R.drawable.sorry__no_chocolate_found
                }
                else -> R.drawable.sorry__no_chocolate_found
            }
        } catch (e: Exception) {
            Log.e("ChocolateMixer", "Error getting chocolate image: ${e.message}")
            R.drawable.chocolate_topping // Safe fallback
        }
    }

    private fun resetSelection() {
        Log.d("ChocolateMixer", "Resetting selection")

        try {
            // Clear selections
            selectedIngredients.clear()

            // Reset all ingredient buttons to normal state
            resetIngredientButton(coconutButton)
            resetIngredientButton(caramelButton)
            resetIngredientButton(waferButton)
            resetIngredientButton(hazelnutButton)
            resetIngredientButton(nougatButton)
            resetIngredientButton(peanutButton)

            // Hide output image
            outputImage.visibility = View.GONE

            // Make sure all buttons are visible
            showIngredientButtons()
            mixButton.visibility = View.VISIBLE

            Log.d("ChocolateMixer", "Selection reset complete")
        } catch (e: Exception) {
            Log.e("ChocolateMixer", "Error resetting selection: ${e.message}")
        }
    }

    private fun resetIngredientButton(imageView: ImageView) {
        imageView.alpha = 1.0f
        imageView.scaleX = 1.0f
        imageView.scaleY = 1.0f
    }
}