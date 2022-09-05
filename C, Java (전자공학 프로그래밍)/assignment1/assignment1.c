/*
학과 : 전자공학과
학번 : 201620837
이름 : 안민규
과목 : 전자공학 프로그래밍
교수님 : 이정원 교수님
*/

#define _CRT_SECURE_NO_WARNINGS
#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include <string.h>

#define WIDTH         10
#define HEIGHT        10
#define FILTER_WIDTH  3
#define FILTER_HEIGHT 3
#define SAME          0
#define VALID         1  //변하지 않는 값으로 지정해놓음

int ppt_filter[3][3] = { {1,2,1}, {2,3,2}, {1,2,1} }; //강의노트에 제시된 필터(필터 종류 3번)

int input_value[HEIGHT][WIDTH];
int filter_value[FILTER_HEIGHT][FILTER_WIDTH];
void printInput(int input_value[][WIDTH]);
void makeInput(int input_value[][WIDTH]);
void makeFilter(int filter_value[][FILTER_WIDTH]);
void printFilter(int filter_value[][FILTER_WIDTH]); //주어진 함수 및 변수 선언
int result_height = 0;
int result_width = 0;
int result[HEIGHT][WIDTH]; //새로 설정한 결과 배열을 위한 변수 선언

void ConvSame() //SAME 방법 함수
{
    int padded_signal[12][12] = { 0, };
    for (int n = 0; n < 12; n++)
    {
        padded_signal[n][0] = 0;
        padded_signal[n][11] = 0;
        padded_signal[0][n] = 0;
        padded_signal[11][n] = 0;
    } //padding 작업

    for (int i = 1; i < 11; i++)
        for (int j = 1; j < 11; j++)
            padded_signal[i][j] = input_value[i - 1][j - 1]; //input을 padded_signal에 넣어 새로운 표본 만들기

    for (int i = 0; i < 10; i++)
        for (int j = 0; j < 10; j++)
            result[i][j] = padded_signal[i][j] * filter_value[2][2] + padded_signal[i][j + 1] * filter_value[2][1] + padded_signal[i][j + 2] * filter_value[2][0]
            + padded_signal[i + 1][j] * filter_value[1][2] + padded_signal[i + 1][j + 1] * filter_value[1][1] + padded_signal[i + 1][j + 2] * filter_value[1][0]
            + padded_signal[i + 2][j] * filter_value[0][2] + padded_signal[i + 2][j + 1] * filter_value[0][1] + padded_signal[i + 2][j + 2] * filter_value[0][0]; //컨벌루션값 결과에 저장

    result_height = 10;
    result_width = 10; //main 함수에서 반복문을 진행하기 위해 높이와 너비 정함
}

void ConvValid() //VALID 방법 함수
{
    for (int i = 0; i < 10; i++)
        for (int j = 0; j < 10; j++)
        {
            result[i][j] = input_value[i][j] * filter_value[2][2] + input_value[i][j + 1] * filter_value[2][1] + input_value[i][j + 2] * filter_value[2][0]
                + input_value[i + 1][j] * filter_value[1][2] + input_value[i + 1][j + 1] * filter_value[1][1] + input_value[i + 1][j + 2] * filter_value[1][0]
                + input_value[i + 2][j] * filter_value[0][2] + input_value[i + 2][j + 1] * filter_value[0][1] + input_value[i + 2][j + 2] * filter_value[0][0];
        } //컨벌루션값 결과에 저장

    result_height = 8;
    result_width = 8; //main 함수에서 반복문을 진행하기 위해 높이와 너비 정함
}

int main()
{
    makeInput(input_value);
    makeFilter(filter_value); // 입력과 필터를 만들어주는 함수 구동

    int mode; // 어떤 방법으로 convolution 진행할지 저장할 변수

    printInput(input_value);
    printFilter(filter_value); // 생성된 입력과 필터를 출력해주는 함수

    printf("진행할 convolution을 정해주세요. (1. SAME 2. VALID) : ");
    scanf("%d", &mode);

    switch (mode) //mode에 입력된 값에 따라 방법 선택하여 convolution 진행
    {
    case 1:
        ConvSame();
        break;
    case 2:
        ConvValid();
        break;
    default:
        printf("잘못된 값을 입력하였습니다. 프로그램이 종료됩니다.");
        return -1; //1과 2가 아닌 다른 숫자 입력시 진행
    }
    printf("결과값\n");
    printf("-----------------------------------------\n");
    for (int i = 0; i < result_height; i++)
    {
        printf("|");
        for (int j = 0; j < result_width; j++)
        {
            printf("%2d |", result[i][j]);
        }

        printf("\n");
    } //same함수와 valid함수에서 미리 설정해놓은 height, width에 따라 반복문 진행
    printf("-----------------------------------------\n");

    return 0;
}

void printInput(int input_value[][WIDTH]) //인풋 출력 함수
{
    printf("\n");
    printf("입력값\n");
    printf("-----------------------------------------\n");
    for (int i = 0; i < HEIGHT; i++)
    {
        printf("|");
        for (int j = 0; j < WIDTH; j++)
        {
            printf("%2d |", input_value[i][j]);
        }

        printf("\n");
    } //주어진 height, width 만큼 반복문 진행하여 생성된 입력 출력
    printf("-----------------------------------------\n");
}

void printFilter(int filter_value[][FILTER_WIDTH]) //필터 출력 함수
{
    printf("필터값\n");
    printf("-------------\n");
    for (int i = 0; i < FILTER_HEIGHT; i++)
    {
        printf("|");
        for (int j = 0; j < FILTER_WIDTH; j++)
        {
            printf("%2d |", filter_value[i][j]);
        }

        printf("\n");
    } //주어진 height, width 만큼 반복문 진행하여 생성된 필터 출력
    printf("-------------\n");
}

void makeInput(int input_value[][WIDTH]) //인풋 생성 함수
{
    int type = 0;
    int max_value = 0;
    printf("어떤 방식으로 입력값을 생성 하시겠습니까?( 1. 랜덤, 2. 모든 값1)\n");
    scanf("%d", &type); //어떤 방법으로 진행할지 입력받음

    switch (type)
    {
    case 1: //랜덤으로 생성
        printf("랜덤 값의 최대 값은? "); //배열에 들어갈 최대 숫자값 입력 받음
        scanf("%d", &max_value);
        for (int i = 0; i < HEIGHT; i++)
            for (int j = 0; j < WIDTH; j++)
                input_value[i][j] = rand() % (max_value + 1); //랜덤으로 숫자를 생성하는 구문
        break;

    case 2: //1로 배열 채움
        for (int i = 0; i < HEIGHT; i++)
            for (int j = 0; j < WIDTH; j++)
                input_value[i][j] = 1;
        break;
    default:
        break;
    }
}

void makeFilter(int filter_value[][FILTER_WIDTH]) //필터 생성 함수
{
    int type = 0;
    int max_value = 0;
    printf("어떤 방식으로 입력값을 생성 하시겠습니까?( 1. 랜덤, 2. 모든 값 1, 3. ppt filter)\n");
    scanf("%d", &type); //어떤 방법으로 진행할지 입력받음

    switch (type)
    {
    case 1: //랜덤 생성
        printf("랜덤 값의 최대 값은? "); //배열에 들어갈 최대 숫자값 입력 받음
        scanf("%d", &max_value);
        for (int i = 0; i < FILTER_HEIGHT; i++)
            for (int j = 0; j < FILTER_WIDTH; j++)
                filter_value[i][j] = rand() % (max_value + 1);
        break;

    case 2: //1로 배열 채움
        for (int i = 0; i < FILTER_HEIGHT; i++)
            for (int j = 0; j < FILTER_WIDTH; j++)
                filter_value[i][j] = 1;
    case 3:
        for (int i = 0; i < FILTER_HEIGHT; i++)
            for (int j = 0; j < FILTER_WIDTH; j++)
                filter_value[i][j] = ppt_filter[i][j];
        break;
    default:
        break;
    }
}