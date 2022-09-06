#ifndef LIST_H
#define LIST_H
#include <iostream>

template <typename T> 
class List {
    private:
        // template <typename T>
        void at(const int error_code);
        class Node {
            friend class List;
            public:
                Node *pNext;
                T data;
                Node(T data=T(), Node *pNext=nullptr) {
                    this -> data = data;
                    this -> pNext = pNext;
                };
        };  
    public:
        List();
        ~List();
        int Size;
        List<T>::Node *head;
        void push_back(T data);
        void push_front(T data);
        T find_and_erase(const T& value);
        int getSize() {return Size;};
        T& operator[](const int index);
};

template <typename T>
List<T>::List() {
    this -> Size = 0;
    this -> head = nullptr;
};

template <typename T>
List<T>::~List() {};


template <typename T>
void List<T>::push_back(T data) {
    if (head == nullptr) {
        head = new List<T>::Node(data);
    } else {
        List<T>::Node *current = this->head;
        while (current->pNext != nullptr) {
            current = current->pNext;
        }
        current->pNext = new List<T>::Node(data);
    }
    Size++;
};


template <typename T>
void List<T>::push_front(T data) {
    if (head == nullptr) {
        head = new List<T>::Node(data);
    } else {
        List<T>::Node *current = this->head;
        head = new List<T>::Node(data);
        head->pNext = current;
    }
    Size++;
};


template <typename T>
T List<T>::find_and_erase(const T& value) {
    List<T>::Node *current = this->head;
    List<T>::Node *prev;
    // std::cout << " <" << current->data << "> " << std::endl;
    if (current->data==value) {
        // std::cout << "OK ";
        this->head = current->pNext;
        this->Size--;
        return current->data;
    }
    
    while (current->pNext != nullptr) {
        prev = current;
        current = current->pNext;
        // std::cout << " <" << current->data << "> " << std::endl;
        if (current->data == value) {
            // std::cout << "OK ";
            prev->pNext = current->pNext;
            this->Size--;
            return current->data;
        }
    }

    return value;
}


template <typename T>
void List<T>::at(const int error_code) {
    if (error_code==0) {
        // code for succes finish
    } else if (error_code==1) {
        throw "IndexError: list index out of range\n";
    }
}


template <typename T>
T & List<T>::operator[](const int index) {
    int clone_index = index;
    int count = 0;
    List<T>::Node *current = this->head;

    if (clone_index < 0) {
        clone_index = this->Size + clone_index;
    }

    while (current!=nullptr) {
        if (count == clone_index) {
            this->at(0);
            return current->data;
        }
        current = current->pNext;
        count++;
    };
    this->at(1);
    return current->data;
    
};


#endif