import { create } from 'zustand'
import { devtools } from 'zustand/middleware'

interface UIState {
    isCreateUserModalOpen: boolean
    openCreateUserModal: () => void
    closeCreateUserModal: () => void
}

export const useUIStore = create<UIState>()(
    devtools(
        (set) => ({
            isCreateUserModalOpen: false,
            openCreateUserModal: () => set({ isCreateUserModalOpen: true }),
            closeCreateUserModal: () => set({ isCreateUserModalOpen: false }),
        }),
        { name: 'UIStore' }
    )
)