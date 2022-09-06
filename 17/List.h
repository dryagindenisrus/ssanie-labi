#ifndef LIST_H
#define LIST_H

template <typename T> 
class List {
    private:
        // template <typename T> 
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
        int getSize() {return Size;};
};

template <typename T>
List<T>::List() {
    this -> Size = 0;
    this -> head = nullptr;
}

template <typename T>
List<T>::~List() {
    
}


template <typename T>
void List<T>::push_back(T data) {
    if (head == nullptr) {
        head = new List<T>::Node(data);
    } else {
        List<T>::Node *current = this -> head;
        while (current->pNext != nullptr) {
            current = current->pNext;
        }
        current->pNext = new List<T>::Node(data);
    }
    Size++;
};



// template<typename T> class Node {
//     public:
//         Node *pNext;
//         T data;
//         Node(T data = T(), Node *pNext = nullptr) {
//             this->data = data;
//             this->nNext = pNext;
//         }
// };


// template<typename T> class List {
//     private:
//         Node<T> *head;
//         int Size;
//     public:
//         List(/* args */);
//         ~List();
//         void push_back(T data);
//         int getSize() {return Size;};
// };

#endif