#define _CRT_SECURE_NO_WARNINGS // для корректной работы scanf()
#include <stdio.h>
// Функция сортировки прямым обменом (метод "пузырька")
void bubbleSort(int *num, int size)
{
  // Для всех элементов
  for (int i = 0; i < size - 1; i++)
  {
    for (int j = (size - 1); j > i; j--) // для всех элементов после i-ого
    {
      if (num[j - 1] < num[j]) // если текущий элемент меньше предыдущего
      {
        int temp = num[j - 1]; // меняем их местами
        num[j - 1] = num[j];
        num[j] = temp;
      }
    }
  }
}
int main()
{
  int a[10] = {4, 54, 23, 121, 6, 76, 45, 45, 90, 56}; // Объявляем массив из 10 элементов
  // Вводим значения элементов массива
  
  bubbleSort(a, 10);  // вызываем функцию сортировки
  // Выводим отсортированные элементы массива
  for (int i = 0; i<10; i++)
    printf("%d ", a[i]);
  getchar(); getchar();
  return 0;
}