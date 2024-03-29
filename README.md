# java-chess

체스 미션 저장소

# DB 연동 방법

``` Bash
cd config
docker-compose up -d
```

# 기능 구현 목록

"status" 명령을 입력하면 각 진영의 점수를 출력하고 어느 진영이 이겼는지 결과를 볼 수 있어야 한다.

## 초기 기물 생성자

- [x] 초기 기물 정보는 각 말들의 시작 위치와 말의 종류를 가진다.
- [x] 체스게임을 진행하기 위한 보드를 생성해준다.

## 보드

- [x] 보드는 체스 말과 위치를 가진다.
- [x] 보드에서 체스 말을 이동시킬 수 있다.
- [x] 보드에서 체스 말을 이동시킬 수 없는 경우 예외가 발생한다.
    - [x] 비어있는 말을 이동시킬 경우
    - [x] 상대 차례의 기물을 움직일 경우
    - [x] 시작지와 목적지의 기물의 색기 같을 경우
- [x] 체스 말과 위치 정보를 반환할 수 있다.
- [x] 둘 중 하나의 킹이 죽었는지 확인할 수 있다.
- [x] 살아있는 킹의 색상을 반환할 수 있다.
    - [x] 두 킹이 살아있거나 죽어있으면 예외를 발생시킨다.

## 체스 말

- [x] 체스 말은 진영의 색깔과 종류를 가진다.
- [x] 체스 말은 출발 위치, 도착 위치, 첫 이동 여부, 말의 위치 정보 목록을 바탕으로 움직일 수 있는지 확인한다.
- [x] 폰의 공격, 이동
    - [x] 공격
        - [x] 대각선으로 공격할 수 있다.
    - [x] 이동
        - [x] 첫 Rank에서 2칸 이동할 수 있다.
        - [x] 앞으로 한 잔 전진
- [x] 나머지 기물의 공격, 이동
    - [x] 나이트
    - [x] 퀸
    - [x] 비숍
    - [x] 룩
    - [x] 킹

## 색깔

- [x] 체스 말의 색깔은 흰색 또는 검정색이다.
- [x] 각 색의 보색을 구할 수 있다.

## 이동 정책

- [x] 방향에 따라 공격 또는 이동을 수행할 수 있다.

## 방향

- [x] 시작 위치와 도착 위치가 주어지면 해당 방향으로 이동할 수 있는지 확인한다.
    - [x] 도착위치 중간에 장애물이 있을 경우 거짓을 반환한다.
    - [x] 이동할 수 있는 방향의 개수를 모두 소진함에도 불구하고 도달하지 못할 경우 거짓을 반환한다.
- [x] 방향은 상하좌우, 대각선, 나이트방향으로 이동한다.
    - [x] 상 방향
    - [x] 하 방향
    - [x] 좌 방향
    - [x] 우 방향
    - [x] 대각선 상좌 방향
    - [x] 대각선 상우 방향
    - [x] 대각선 하좌 방향
    - [x] 대각선 하우 방향
    - [x] 나이트 방향
- [x] 방향을 조합하여 여러 위치 정보들을 반환할 수 있다.
- [x] 특정 출발 위치에서 동작하는 방향이 존재한다.

## 장애물

- [x] 장애물은 위치들을 가지고 있다.
- [x] 현재 위치에서 목적지에 도달하기 전 가지고 있는 위치와 비교하여 가는 방향이 막힐 경우 참을 반환한다.

## 위치

- [x] 위치는 가로(File), 세로(Rank) 값을 가진다.
- [x] 위치의 가로, 세로 범위는 각각 1 ~ 8 이다.
- [x] 위치가 경계 값인지 확인한다.
    - [x] Rank값이 최소인지 확인한다.
    - [x] Rank값이 최대인지 확인한다.
    - [x] File값이 최소인지 확인한다.
    - [x] File값이 최대인지 확인한다.
- [x] 백터가 주어지면 더할 수 있는지 확인한다.

## 가로(File)

- [x] File 값은 a~h 값을 가진다.
- [x] 정수 값을 이용해 오른쪽 및 왼쪽을 이동할 수 있다.
- [x] 이동 전 유효한 이동인지 확인할 수 있다.

## 세로(Rank)

- [x] Rank 값은 1~8 값을 가진다.
- [x] 정수 값을 이용해 위 및 아래를 이동할 수 있다.
- [x] 이동 전 유효한 이동인지 확인할 수 있다.

## 백터

- [x] 위치를 갱신할 수 있는 값을 가진다.

## 입출력

- [x] 입력 기능 구현
    - [x] 명령어 정의 및 입력 구현
    - [x] 위치 정보 입력 구현

- [x] 출력 기능 구현
    - [x] 명령어 도움말 출력
    - [x] 체스 보드 출력
    - [x] 각 색깔의 점수와 이기고 있는 색깔 출력
    - [x] 승리한 색깔 출력

## 체스게임

- [x] 게임 시작 종료
    - [x] start는 보드를 초기화하고 시작한다.
    - [x] end는 게임을 종료한다.
- [x] move 기능 구현
    - [x] 이동 위치를 입력 받는다.
    - [x] King이 잡혔을 때 게임을 종료
- [x] status 기능 구현
    - [x] 현 게임의 점수를 계산하고 이기고 있는 색깔을 출력한다.

## 게임 결과

- [x] 게임 결과를 계산한다.
    - [x] 승리한 혹은 승리하고 있는 색깔을 반환한다.
        - [x] 두 진영 중 한개의 킹이 죽을 경우
        - [x] 두 진영 중 두개의 킹이 살아있는 경우 점수로 판단한다.
    - [x] 두 색깔의 체스 말에 대한 점수 계산

## 점수

- [x] 게임 점수를 계산
- [x] 두 점수를 비교

## DB 연동

- [ ] Terminate() 명령어 입력 시 게임을 DB에 업데이트 한다.
- [ ] Start 명령어 입력시 DB에 게임을 불러온다.
    - [ ] DB에 자료가 없으면 새로운 게임을 생성한다.
- [ ] EndGame()이 발생하면 DB의 기록을 지운다.
