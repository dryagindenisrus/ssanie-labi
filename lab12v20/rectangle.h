#ifndef MY_LABH
#define MY_LABH

class Rectangle {
private: // прописываем приватные поля класса
	float height;
	float width;
public: // прописываем публичные поля класса
	Rectangle(/* args */);
	~Rectangle();  
	void print();
	void set(float height, float width); 
	float getWidth();
	float getHeight();
	float perimetr();
	float area();
};

#endif
