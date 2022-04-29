#include <iostream>
#include "rectangle.h"

using namespace std;

Rectangle::Rectangle() {}
Rectangle::~Rectangle() {}

void Rectangle::set(float height, float width) {
	if (height <=20.0 && height > 0.0) {
		this -> height = height;
	} else {
		throw invalid_argument("parametr HEIGHT \">0\" and \"<=20\""); // елси параметр не соответствует требованиям - выводится исключение
	}
	if (width <=20 && width > 0) {
		this -> width = width;
	} else {
		throw invalid_argument("parametr WIDTH \">0\" and \"<=20\"");  // елси параметр не соответствует требованиям - выводится исключение
	}	
}

float Rectangle::getHeight() {
	return height;
} // описание метода для получения высоты прямоугольника

float Rectangle::getWidth() {
	return width;
} // описание метода для получения ширины прямоугольника

float Rectangle::area() {
	return width * height; 
} // описание метода для получения площади прямоугольника

float Rectangle::perimetr() {
	return 2 * (width + height);
} // описание метода для получения периметра прямоугольника

void Rectangle::print() {
	int height_p = height; //избавляемся от дробной части высоты прямоугольника
	int width_p = width; //избавляемся от дробной части ширины прямоугольника

	for (int i=0; i<height_p; i++) { // количество рядов (высота прямоугольника)
		for (int j = 0; j < width_p*2; j++) { // символов в ряде (ширина прямоугольника), умножем ширину на 2, чтобы получить более-менее пропорциониальный прямоугольник
		// так как клетка символа прямоугольной формы
			cout << char(219); // 219 символ в таблице - это закарашенная клетка
		}
		cout << "\n"; 
	}
} // графический вывод прямоугольника
