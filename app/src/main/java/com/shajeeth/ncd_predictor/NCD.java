package com.shajeeth.ncd_predictor;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class NCD extends AppCompatActivity {
    double bmi;
    TextView category_text;
    TextView possibility_text;
    TextView diet_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ncd);
        Bundle bundle = getIntent().getExtras();
        bmi = bundle.getDouble("bmi");

        category_text = findViewById(R.id.category);
        possibility_text = findViewById(R.id.possibilities);
        diet_text = findViewById(R.id.diet);

        String category = find_category(bmi);
        String possibilities = "";
        String diet = "";
        if (category.equals("Under Weight")){
            possibilities = "Blood Pressure: Low\nDiabetes: Risk\nCancer: Risk(Lung or Blood)\nArthritis: Risk";
            diet = "•\tBeing underweight can be just as unhealthily as being obese. It’s important to eat mostly healthy foods when you are trying to gain weight.\n" +
                    "•\tEat more calories than your body burns to GAIN WEIGHT.\n" +
                    "\uF0D8\tFor slow weight gain: 300-500 calories per day\n" +
                    "\uF0D8\tFor fast weight gain:700 -1000 calories per day\n" +
                    "•\tEat,\n" +
                    "1.\tPlenty of proteins\n" +
                    "2.\tAdequate amount of Carbs & Fat\n" +
                    "3.\t3 meals per day (at least)\n" +
                    "4.\tTake energy-dense foods & spices \n" +
                    "•\tFood Suggestions :\n" +
                    "1.\tNuts (Almonds, Walnuts, Peanuts)\n" +
                    "2.\tDried Fruit (Raisins, Dates)\n" +
                    "3.\tHigh-fat diary (Whole Milk, nonfat yoghurt)\n" +
                    "4.\tGrains ( Oats, Brown rice)\n" +
                    "5.\tMeat & Fish\n" +
                    "6.\tTubers (Potatoes, Sweet potatoes)\n" +
                    "7.\tDark chocolate\n" +
                    "8.\tAvocados\n" +
                    "9.\tCoconut Milk\n" +
                    "10.\tPeanut butter";
        }else if (category.equals("obese")){
            possibilities = "Blood Pressure: Risk\nCholesterol: Risk\nDiabetes: Risk\nCancer: Risk\nFat Liver: Risk\n Arthritis: Risk\nHeart Disease: Risk";
            diet = "•\tIncreasing your activity level might be enough if you only need to lose a very few Kgs to get your BMI into a healthy range.\n" +
                    "•\t The only way to lose weight is to eat fewer calories than you burn in a day.\n" +
                    "•\tEating fewer calories doesn’t necessarily meal eating less food.\n" +
                    "•\tEat,\n" +
                    "1.\tLess cholesterol & less fat products. (canola Oil, Olive Oil)\n" +
                    "Also avoid less saturated & less trans-fat products.\n" +
                    "E.g. \n" +
                    "\uF0D8\tFat in meat products\n" +
                    "\uF0D8\tButter\n" +
                    "\uF0D8\tMargarine\n" +
                    "\uF0D8\tFat coming from fried foods.\n" +
                    "2.\tMore fruits, Vegetables.\n" +
                    "3.\tWhole grains.\n" +
                    "Also avoid foods that high in sugar like pastries, soda and flavored drinks.\n" +
                    "4.\tNon-fat dairy products.\n" +
                    "5.\tEating 5 smaller meals than eating 3 larger ones.";
        }

        category_text.setText(category);
        possibility_text.setText(possibilities);
        diet_text.setText(diet);



    }

    public String find_category(double bmi){
        String category = "";
        if (bmi>=18.5 && bmi <= 22.9){
            category = "Normal";
        }else if (bmi<18.5){
            category = "Under Weight";
        }else if (bmi>=23){
            category = "obese";
        }

        return category;
    }
}
