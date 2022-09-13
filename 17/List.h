#ifndef LIST_H
#define LIST_H
#include <iostream>
#include <exception>
#include <stdexcept>


struct Point { int x; int y; int z; };


class ListException: public std::exception {
    private:
    	std::string m_error;
    public:
    	ListException(std::string error)
    		: m_error(error) {}
    	const char* what() const noexcept { 
            return m_error.c_str(); 
        }
};



template <typename T> 
class List {
    private:
        void at(const ListException e);
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
        List<T> intersection_of_lists(List<T> list2);
        void push_back(T data);
        void push_front(T data);
        T find_and_erase(const T& value);
        T find(int index);
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
T List<T>::find(int index) {
    int counter = 0;
    List<T>::Node *current = this->head;
    List<T>::Node *prev;
    // std::cout << " <" << current->data << "> " << std::endl;
    if (counter == index) {
        // std::cout << "OK ";
        this->head = current->pNext;
        this->Size--;
        return current->data;
    }
    
    while (current->pNext != nullptr) {
        try {
            prev = current;
            current = current->pNext;
            counter++;
            // std::cout << " <" << current->data << "> " << std::endl;
            if (counter == index) {
                // std::cout << "OK ";
                prev->pNext = current->pNext;
                this->Size--;
                return current->data;
            }
            if (counter != index && current->pNext == nullptr) {
                throw ListException("Cannot find element");
            }
        } catch (ListException &e) {
            this->at(e);
        }        
    }
    return current->data;
}



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
        try {
            prev = current;
            current = current->pNext;
            // std::cout << " <" << current->data << "> " << std::endl;
            if (current->data == value) {
                // std::cout << "OK ";
                prev->pNext = current->pNext;
                this->Size--;
                return current->data;
            }
            if (current->data != value && current->pNext == nullptr) {
                throw ListException("Cannot find element");
            }
        } catch (ListException &e) {
            this->at(e);
        }        
    }
    return value;
}


template <typename T>
void List<T>::at(const ListException e) {
    std::cerr << "\n\x1B[31mListException:\033[0m\t" << e.what() << std::endl;
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
        try {
            if (count == clone_index) {
                return current->data;
            }
            current = current->pNext;
            count++;
            if (current==nullptr && count != clone_index) {
                throw ListException("Index list out od range");
            }
        } catch (ListException &e) {
            this->at(e);
        }
    };
    return current->data;
    
};

template <typename T>
List<T> List<T>::intersection_of_lists(List<T> list2) {
    List<T> intersection;
    // std::cout << "list1: " << list1[0] << "\nlist2: " << list2[0] << std::endl;
    for (int i = 0; i<this->getSize(); i++) {
        for (int j = 0; j<list2.getSize(); j++) {
            if (this->find(i) == list2[j]) {
                
                intersection.push_back(list2[i]);
            }
        }
    }
    return intersection;
}


#endif