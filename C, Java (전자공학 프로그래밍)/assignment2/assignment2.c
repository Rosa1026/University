/*
학과 : 전자공학과
학번 : 201620837
이름 : 안민규
과목 : 전자공학 프로그래밍
교수님 : 이정원 교수님
*/
#define _CRT_SECURE_NO_WARNINGS
#define MEMORY_SIZE 100
#define VARIABLE_LENGTH 10
#include <stdio.h>
#include <stdbool.h>
#include <string.h>
#include <stdlib.h> //exit()함수를 사용하기 위해 불러옴

void updateMemory(int menu);
void viewMemory();
void allocateVariable();
void deleteVariable(); //사용할 함수들을 미리 선언

int stack_pointer = 0, memory_address[MEMORY_SIZE] = { 0 }, array_pointer = 0, array_size[MEMORY_SIZE] = { 0 }; //stack 속 위치를 나타내는 stack_pointer, 0층부터 memory_size 만큼의 층을 갖는 memory_address stack, 추후에 입력되는 array_size를 저장하기 위해 새로운 스택 생성 및 위치를 알려주는 array_pointer 선언
char variable_table[MEMORY_SIZE][2][VARIABLE_LENGTH] = { 0 }; //0층부터 Memory_size까지의 층을 가지며, 한층에 2칸, 한 칸 당 variable_length의 입력을 받을 수 있는 table 선언

int main() {
	float check_num; //메뉴 선택을 위해 check_num이라는 float형 변수 선언, 정수가 아닌 값이 입력됐을 때를 확인하기 위해 float형 변수로 선언
	int menu; //후에 updatememory 호출을 위한 int형 변수 선언

	do {
		printf("1: exit\n");
		printf("2: memory view\n");
		printf("3: variable allocation\n");
		printf("4: variable delete\n");
		printf(">> select memu : "); //선택할 메뉴 프린트

		if (scanf("%f", &check_num) != 1 || (int)check_num != check_num) {  
			while (getchar() != '\n');
			system("cls");
			printf("Please enter an integer\n\n");
			continue;
		} //입력된 값이 정수가 아니라면 정수를 입력하게끔 다시 선언
		else if ((int)check_num < 1 || (int)check_num > 4) {
			system("cls");
			printf("Please enter the correct menu.\n\n");
			continue;
		} //입력된 숫자가 1보다 작거나 4보다 크면 옳바른 메뉴를 고르게함
		else if ((int)check_num == 1) {
			printf("\n\nExit the program.\n\n");
			break;
		} //입력된 숫자가 1이라면 프로그램 종료

		system("cls"); //프로세스의 생성&대기&대체의 기능을 하는 system 함수 호출
		menu = check_num; //menu에 입력된 check_num의 값을 저장/ 이미 위에서 int형으로 변환되어 저장 가능
		printf("  selected menu : %d\n\n", menu); 
		updateMemory(menu); //menu로 updateMemory 호출

	} while (1); //do while 문

	return 0;
}

void updateMemory(int menu) {
	void (*function_pointer)(); //포인터함수 선언

	if (menu == 2) {
		function_pointer = viewMemory;
		function_pointer(); //선택된 메뉴가 2라면 할당된 메모리를 보기 위해 viewmemory 함수동작
	}
	else if (menu == 3) {
		function_pointer = allocateVariable;
		function_pointer(); //선택된 메뉴가 3라면 변수를 할당하기 위해 allocatevariable 함수동작
	}
	else if (menu == 4) {
		function_pointer = deleteVariable;
		function_pointer(); //선택된 메뉴가 4라면 할당된 변수를 지우기 위해 deletevariable 함수동작
	}
}

void viewMemory() {
	printf("\nmemory address\n------\n");
	for (int n = 0; n < MEMORY_SIZE; n++) { //0부터 memory_size까지 탐색
		printf("%3d |", n);
		if (memory_address[n] != 0) { //memory_address[n]이 0이 아닐때까지 진행 -> 즉, 메모리가 할당되어 있음
			printf(" } ");
			if (memory_address[n] == 2) //memory_address[n]이 2라면 변수를 처음 할당한 위치이므로 변수의 이름과 종류 배열의 사이즈를 보여줌 
				printf("%s %s[%d]", variable_table[n][1], variable_table[n][2], array_size[n]); //메모리가 들어있는 n을 찾아냈다면, 그 때 variable_table의 n번째 1,2. 즉, 할당한 변수의 종류와 이름을 출력/또한 array_size에 저장된 n번째 숫자를 불어옴
		}
		printf("\n");
	}
	printf("------\n\n\n");
}

void allocateVariable() {
	void (*function_pointer)();
	int variable_size = 0, size = 0; //변수의 크기와 배열의 크기를 받기 위한 int형 변수선언
	char variable_type[VARIABLE_LENGTH], variable_name[VARIABLE_LENGTH]; //변수의 이름과 타입을 입력받기 위해 char형 변수 선언

	printf("variable_type : ");
	scanf("%s", variable_type);
	printf("variable_name : ");
	scanf("%s", variable_name);
	printf("array_size : ");
	scanf("%d", &array_size[array_pointer]); //변수의 이름, 종류, 배열 수를 입력받음. 배열 수는 먼저 전역변수로 선언된 array_size[] stack에 저장

	size = array_size[array_pointer]; //배열에 저장된 값을 따로 size에 저장

	strcpy(variable_table[stack_pointer][1], variable_type);
	strcpy(variable_table[stack_pointer][2], variable_name); //입력받은 변수의 이름과 타입을 variable_table의 stack_pointer층 1,2칸에 저장


	if (strcmp(variable_type, "char") == 0)
		variable_size = 1;
	else if (strcmp(variable_type, "int") == 0)
		variable_size = 4;
	else if (strcmp(variable_type, "float") == 0)
		variable_size = 4;
	else if (strcmp(variable_type, "double") == 0)
		variable_size = 8; //입력받은 variable_type과 문자열을 비교하여 변수의 종류에 맞추어 variable_size에 값을 저장

	if (variable_size * size > MEMORY_SIZE)
	{
		printf("Variable size is too large.");
		exit(0);
	} //예외처리 (메모리할당 불가), 입력받은 변수의 size와 배열의 크기의 곱이 스택의 사이즈 memory_size보다 크다면 할당불가 프린트하고 프로그램 종료

	if (MEMORY_SIZE - stack_pointer < (variable_size) * (array_size[array_pointer]))
	{
		for (int i = 0; i < MEMORY_SIZE; i++)
		{
			*variable_table[i][1] = 0;
			*variable_table[i][2] = 0;
			array_size[i] = 0;
			memory_address[i] = 0;
		} //이미 들어가있는 variable_table, array_size, memory_address를 0으로 초기화
		stack_pointer = 0;
		array_pointer = 0; //새로 시작해야하므로 stack_pointer와 array_pointer를 0으로 초기화
		strcpy(variable_table[stack_pointer][1], variable_type);
		strcpy(variable_table[stack_pointer][2], variable_name); //초기화 전에 입력된 문자열을 다시 복사해서 variable_table에 삽입. 이 때 stack_pointer가 0이므로 0층부터 다시 저장
		array_size[array_pointer] = size; //size에 저장해놓은 값을 초기화된 array_pointer층의 array_size에 다시 저장. 이를 위해 size에 미리 값을 저장.
	} //예외처리 (자리 안 남았을 때), 입력한 변수의 할당량보다 메모리가 적게 남았을 경우, 초기화 후 0층부터 다시 진행.

	memory_address[stack_pointer] = 2; //stack_pointer층의 memory stack에는 2를 저장. 입력받은 층이기 때문에
	for (int n = 1; n < variable_size; n++)
		memory_address[stack_pointer + n] = 1; //입력받은 변수의 크기만큼 1을 저장.

	stack_pointer += variable_size; //stack_pointer를 변수의 크기만큼 증가.

	for (int i = 1; i < size; i++)
	{
		for (int n = 0; n < variable_size; n++)
			memory_address[stack_pointer + n] = 1;
		stack_pointer += variable_size;
	} //입력된 배열의 크기만큼 1을 채워주는 반복문.

	array_pointer = stack_pointer; //array_pinter를 stack_pointer의 값과 같게 만듬. 메모리를 저장하는 memory_address와 배열의 값을 저장하는 array_size가 같은 층에 있게하기 위함

	function_pointer = viewMemory;
	function_pointer(); //viewmemory 함수 호출
}

void deleteVariable() {
	void (*function_pointer)();
	int stack = 0; //배열의 수만큼 지우기 위해 얼만큼의 스택이 쌓여있는지 알기위해 선언한 변수
	int variable_size = 0; //변수의 크기만큼 지워야하므로 이를 알기위한 변수
	char variable_name[VARIABLE_LENGTH]; //삭제할 변수의 이름을 받기 위해 char 변수 선언

	printf("variable_name : ");
	scanf("%s", variable_name); //삭제할 변수 이름 입력 받음

	for (int i = 0; i < MEMORY_SIZE; i++) { //0부터 원하는 변수의 종류까지 탐색을 함. 이 때 탐색된 위치값을 i라 함
		if (strcmp(variable_name, variable_table[i][2]) == 0) {
			if (strcmp(variable_table[i][1], "char") == 0)
				variable_size = 1;
			else if (strcmp(variable_table[i][1], "int") == 0)
				variable_size = 4;
			else if (strcmp(variable_table[i][1], "float") == 0)
				variable_size = 4;
			else if (strcmp(variable_table[i][1], "double") == 0)
				variable_size = 8; //입력된 변수의 이름과 문자열을 비교해 그에 맞는 변수의 크기를 입력함

			for (int n = 0; n < variable_size; n++)
				memory_address[i + n] = 0; //0부터 변수의 크기까지 스택에 저장된 값을 0으로 초기화, 하지만 딱 변수의 크기만큼만 반복

			stack = i; //탐색된 위치의 값을 stack이라는 변수에 저장함. 후에 배열만큼 지우기 위해 선언함

			for (int m = 1; m < array_size[i]; m++) //1부터 배열의 크기만큼 반복
			{
				for (int n = 0; n < variable_size; n++) //앞서 i에서 변수의 크기만큼 진행했다면 미리 i의 값을 stack값에 넣어 stack에 저장된 값에 변수의 크기 및 n만큼 더함
					memory_address[stack+variable_size + n] = 0; //이는 이미 앞에서 변수의 크기만큼 진행을 하였으므로 그 이후의 값을 지우기위함
				stack += variable_size; //변수의 크기만큼 진행을 했으므로 stack에 변수의 크기를 저장해 stack값 증가. 따라서 배열만큼 지울 수 있음
			}

			*variable_table[i][1] = 0;
			*variable_table[i][2] = 0; //variable_table 초기화
			array_size[i] = 0; //탐색된 위치의 variable_table 및 array_size 0으로 초기화
			break;
		}
	}

	function_pointer = viewMemory;
	function_pointer(); //viewmemory 함수 호출
}