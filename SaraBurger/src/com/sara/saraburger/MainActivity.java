package com.sara.saraburger;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.sara.foodslist.Category;
import com.sara.foodslist.CategoryList;
import com.sara.foodslist.FoodItem;

public class MainActivity extends Activity {

	private CategoryList saraList = new CategoryList("Sara Burger");

	private Button addItem;
	private Button removeItem;
	private Button updateItem;
	private Button searchItem;
	private Button showItems;

	private EditText cText;
	private EditText fText;
	private EditText SText;
	private EditText pText;
	private EditText dText;
	private EditText qText;

	private List<List<HSSFCell>> sheetData = new ArrayList<List<HSSFCell>>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		//saraList.addFoodItem(new Category("Burger"), new FoodItem("H1", 2));

		try {
			this.buildFoodExcel("fooditem.xls");
			saraList = this.linkedListCreater();
		} catch (IOException e) { // TODO Auto-generated catch block
			e.printStackTrace();
		}

		cText = (EditText) findViewById(R.id.categoryText);
		fText = (EditText) findViewById(R.id.foodText);
		SText = (EditText) findViewById(R.id.sizeText);
		pText = (EditText) findViewById(R.id.priceText);
		dText = (EditText) findViewById(R.id.descriptionText);
		qText = (EditText) findViewById(R.id.quantityText);

		Intent intent = new Intent(MainActivity.this, LoginActivity.class);

		startActivity(intent);

		final Intent intentResult = new Intent(MainActivity.this,
				ResultActivity.class);

		addItem = (Button) findViewById(R.id.addItem);
		addItem.setOnClickListener(new Button.OnClickListener() {
			@Override
			public void onClick(View v) {

				if (isEmpty(cText))
					cText.setError("Please enter");
				if (isEmpty(fText))
					fText.setError("Please enter");
				if (isEmpty(qText))
					qText.setError("Please enter");
				if (!isEmpty(cText) && !isEmpty(fText) && !isEmpty(qText)
						&& (isEmpty(SText) || isEmpty(pText) || isEmpty(dText)))
					saraList.addFoodItem(
							new Category(cText.getText().toString()),
							new FoodItem(fText.getText().toString(), Integer
									.parseInt(qText.getText().toString())));
				else if (!isEmpty(cText) && !isEmpty(fText) && !isEmpty(qText)
						&& !isEmpty(SText) && !isEmpty(pText)
						&& !isEmpty(dText))
					saraList.addFoodItem(
							new Category(cText.getText().toString()),
							new FoodItem(fText.getText().toString(), SText
									.getText().toString(), Double
									.parseDouble(pText.getText().toString()),
									dText.getText().toString(), Integer
											.parseInt(qText.getText()
													.toString()), true));

				cText.setText("");
				fText.setText("");
				SText.setText("");
				pText.setText("");
				dText.setText("");
				qText.setText("");
			}
		});

		removeItem = (Button) findViewById(R.id.removeItem);
		removeItem.setOnClickListener(new Button.OnClickListener() {
			@Override
			public void onClick(View v) {
				if (isEmpty(cText))
					cText.setError("Please enter");
				if (!isEmpty(cText) && isEmpty(fText) && isEmpty(qText))
					saraList.removeCategory(cText.getText().toString());
				if (!isEmpty(cText) && !isEmpty(fText) && isEmpty(qText))
					saraList.getList()
							.seekNode(cText.getText().toString())
							.getValue()
							.removeFoodNode(
									fText.getText().toString());
				if (!isEmpty(cText) && !isEmpty(fText) && !isEmpty(qText))
					saraList.getList()
							.seekNode(cText.getText().toString())
							.getValue()
							.removeFood(
									new FoodItem(fText.getText().toString(),
											Integer.parseInt(qText.getText()
													.toString())));
				cText.setText("");
				fText.setText("");
				SText.setText("");
				pText.setText("");
				dText.setText("");
				qText.setText("");
			}
		});

		updateItem = (Button) findViewById(R.id.updateItem);
		updateItem.setOnClickListener(new Button.OnClickListener() {
			@Override
			public void onClick(View v) {
				if (isEmpty(cText))
					cText.setError("Please enter");
				if (isEmpty(fText))
					fText.setError("Please enter");
				if (!isEmpty(cText) && !isEmpty(fText))
					if (saraList.getList().findNode(cText.getText().toString()))
						if (saraList.getList()
								.seekNode(cText.getText().toString())
								.getValue().getList()
								.findNode(fText.getText().toString()))
							saraList.getList()
									.seekNode(cText.getText().toString())
									.getValue()
									.getList()
									.seekNode(fText.getText().toString())
									.setValue(
											new FoodItem(fText.getText()
													.toString(), SText
													.getText().toString(),
													Double.parseDouble(pText
															.getText()
															.toString()), dText
															.getText()
															.toString(),
													Integer.parseInt(qText
															.getText()
															.toString()), true));
						else
							fText.setError("Food not found");
					else
						cText.setError("Category not found");
				cText.setText("");
				fText.setText("");
				SText.setText("");
				pText.setText("");
				dText.setText("");
				qText.setText("");
			}
		});

		searchItem = (Button) findViewById(R.id.searchItem);
		searchItem.setOnClickListener(new Button.OnClickListener() {
			@Override
			public void onClick(View v) {
				String result = "";
				if (isEmpty(cText) && isEmpty(fText)) {
					cText.setError("Please enter");
					fText.setError("Please enter");
				}
				if (!isEmpty(cText) && isEmpty(fText)) {
					// search category
					result = saraList.getList()
							.seekNode(cText.getText().toString()).toString();
				}
				if (!isEmpty(cText) && !isEmpty(fText)) {
					// search food in category
					result = saraList.getList()
							.seekNode(cText.getText().toString()).getValue()
							.getList().seekNode(fText.getText().toString())
							.toString();

				}
				intentResult.putExtra("com.sara.saraburger.saraList", result);
				startActivity(intentResult);
			}
		});

		showItems = (Button) findViewById(R.id.showAll);
		showItems.setOnClickListener(new Button.OnClickListener() {
			@Override
			public void onClick(View v) {

				intentResult.putExtra("com.sara.saraburger.saraList",
						saraList.toString());

				startActivity(intentResult);

			}
		});

	}

	private boolean isEmpty(EditText etText) {
		return etText.getText().toString().trim().length() == 0;
	}

	public void buildFoodExcel(String fileName) throws IOException {

		InputStream fis = null;

		//AssetManager assetManager = getAssets();

		try {// /SaraBurger/assets/fooditem.xls

			fis = getAssets().open(fileName);

			HSSFWorkbook workbook = new HSSFWorkbook(fis);
			HSSFSheet sheet = workbook.getSheetAt(0);
			Iterator<Row> rows = sheet.rowIterator();
			while (rows.hasNext()) {
				HSSFRow row = (HSSFRow) rows.next();
				Iterator<Cell> cells = row.cellIterator();
				List<HSSFCell> data = new ArrayList<HSSFCell>();
				while (cells.hasNext()) {
					HSSFCell cell = (HSSFCell) cells.next();
					data.add(cell);
				}
				sheetData.add(data);
				//assetManager.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (fis != null) {
				fis.close();
			}
		}
	}


	public CategoryList linkedListCreater() {
		Category category;
		CategoryList foodList = new CategoryList("Sara Burger");
		for (int i = 0; i < sheetData.size(); i++) {
			List<?> list = (List<?>) sheetData.get(i);
			Cell cell = (Cell) list.get(0);
			String categoryName = cell.getStringCellValue();
			cell = (Cell) list.get(1);
			String name = cell.getStringCellValue();
			cell = (Cell) list.get(2);
			String size = cell.getStringCellValue();
			cell = (Cell) list.get(3);
			double price = cell.getNumericCellValue();
			cell = (Cell) list.get(4);
			String description = cell.getStringCellValue();
			cell = (Cell) list.get(5);
			int quantity = (int) cell.getNumericCellValue();
			cell = (Cell) list.get(6);
			boolean specialOrder = cell.getBooleanCellValue();
			category = new Category(categoryName);
			if (foodList.getList().findNode(categoryName)) {
				foodList.Category(categoryName)
						.getValue()
						.addFood(
								new FoodItem(name, size, price, description,
										quantity, specialOrder));
			} else {
				category.addFood(new FoodItem(name, size, price, description,
						quantity, specialOrder));
				foodList.addCategory(category);
			}
		} // end of for loop
		return foodList;
	}
}
