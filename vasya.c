#include <stdio.h>

int deleting(int arr[], int raz);
int testing(void);
int abs(int input);
int srav(int arr1[], int arr2[], int raz);

int main() {
    testing();
}
int testing() {
    // function for testing program
    //intput data in array
    int input_data[][3][10] = {
        {{223,676,132,1234,123,12,214,655,45,238}, {223,676,0,1234,123,0,214,655,0,238}},
        {{9,56,124,675,345,647,234,575,233,657}, {9,56,124,675,0,647,0,575,0,657}},
        {{456,436,787,523,634,340,123,213,433,54}, {456,0,787,0,634,340,0,213,433,54}},
        {{1,4,5,23,67,34,123,23,43,213}, {1,4,5,23,67,0,123,0,43,213}},
    };
    int razmer = sizeof input_data[0][1][1] * 5 / 2 ;

    //in cycle check output data and print status of complete program
    for (int i = 0; i < 4; i++) {
        char status;
        status = 'F';
        // just execute the function to zero out the local minimum
        deleting(input_data[i][0], razmer);
        // here is the output of tests
        if (srav(input_data[i][0], input_data[i][1], razmer)) { 
            status = 'T';
        }
        printf("input: [");
        for (int j = 0; j<10; j++) {
            printf("%d,", input_data[i][0][j]);
        }
        printf("]\n     [");
        for (int j = 0; j<10; j++) {
            printf("%d,", input_data[i][1][j]);
        }
        printf("] output:%c\n", status);
    }

}

int srav(int arr1[], int arr2[], int raz){
    int stat = 1;
    for (int i = 0; i < raz; i++) {
        // through a loop, compare 2 arrays: processed and an array for checking
        if (arr1[i] != arr2[i]) {
            // if the elements are not equal then automatically return 0
            stat = 0;
        }
    }
    return stat;
}

int deleting(int arr[], int raz) {
    int n = 10, i;
    for (i = 2; i < raz; i++) {
        // check adjacent elements
        if ((arr[i-2] > arr[i-1]) && (arr[i-1] < arr[i])) {
            arr[i-1] = 0;
        }
    }
    return 0;
}

