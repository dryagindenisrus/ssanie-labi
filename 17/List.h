#ifndef LIST_H
#define LIST_H

// template<typename T> class List {
//     private:
//         template<typename T> class Node {
//             friend class List;
//             public:
//                 Node *pNext;
//                 T data;
//                 Node(T data=T(), Node *pNext=nullptr) {
//                     this -> data = data;
//                     this -> pNext = pNext;
//                 };
//         };  
//     public:
//         int getSize() {return Size;};
//         List(/* args */);
//         ~List();
//         int Size;
//         Node<T> *head;
//         void push_back(T data);
// };

// template <typename T>
// List<T>::List(/* args */) {
//     this -> Size = 0;
//     this -> head = nullptr;
// }

// template <typename T>
// List<T>::~List() {
// }



template<typename T> class Node {
    public:
        Node *pNext;
        T data;
        Node(T data = T(), Node *pNext = nullptr) {
            this->data = data;
            this->nNext = pNext;
        }
};


template<typename T> class List {
    private:
        Node<T> *head;
        int Size;
    public:
        List(/* args */);
        ~List();
        void push_back(T data);
        int getSize() {return Size;};
};

#endif